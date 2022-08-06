#!/usr/bin/env bash

function print() {
    line=$(caller)
    echo $line ":" $@
}

go build
gitHJN=$(realpath ./gitHJN)
function gitHJN(){
  $gitHJN $@
}

testdir="../git-test"
if [ -e "$testdir" ]; then
    rm -rf $testdir
fi

mkdir $testdir
cd $testdir

print "test git init"
gitHJN init gitHJNDir
git init gitDir
echo -e

print "test git hash-object"
echo "version1" > gitHJNDir/file.txt
echo "version1" > gitDir/file.txt
gitHJN hash-object -w gitHJNDir/file.txt > hash1
git hash-object -w gitDir/file.txt > hash2
# 比较sha256值是否相同
cmp hash1 hash2 --print-chars
echo -e

print "test git cat-file"
h1=$(<hash1)
h2=$(<hash2)
gitHJN cat-file -p $h1 > fileContent1
git cat-file -p $h2 > fileContent2
# 比较文件内容是否相同
cmp fileContent1 fileContent2 --print-chars
gitHJN cat-file -t $h1 > fileType1
git cat-file -t $h2 > fileType2
# 比较object类型是否相同
cmp fileType1 fileType2 --print-chars
gitHJN cat-file -s $h1 > fileSize1
git cat-file -s $h2 > fileSize2
# 比较文件大小是否相同
cmp fileSize1 fileSize2 --print-chars
echo -e

print "test git update-index --add, git ls-files --stage"
# print "cd gitHJNDir"
cd gitHJNDir
# print "gitHJN update-index --add file.txt"
gitHJN update-index --add file.txt
# print "gitHJN ls-files --stage > ../stage1"
gitHJN ls-files --stage > ../stage1
# print "cd ../gitDir"
cd ../gitDir
# print "git update-index --add file.txt"
git update-index --add file.txt
# print "git ls-files --stage > ../stage2"
git ls-files --stage > ../stage2
# print "cd .."
cd ..
# print "cmp stage1 stage2 --print-chars"
cmp stage1 stage2 --print-chars
# print "echo -e"
echo -e

print "test git write-tree"
cd gitHJNDir
mkdir dir
echo 123 > dir/file1
echo abc > dir/file2
gitHJN update-index --add dir/file1
gitHJN update-index --add dir/file2
gitHJN write-tree > treeobj1sha1
gitHJN cat-file -p $(<treeobj1sha1) > treeobjContent

# 文件夹已经存在
# mkdir ../../test-data

# cd ../gitDir
# mkdir dir
# echo 123 > dir/file1
# echo abc > dir/file2
# git update-index --add dir/file1
# git update-index --add dir/file2
# git write-tree > treeobj1
# git cat-file -p $(<treeobj1) > ../../test-data/test-write-tree-data.txt

# cd ../gitHJNDir
cmp treeobjContent ../../test-data/test-write-tree-data.txt
echo -e


print "test commit-tree, log"
gitHJN commit-tree $(<treeobj1sha1) -m 'first commit' > commit1Sha1
echo version2 > file.txt
gitHJN update-index --add file.txt
gitHJN write-tree > treeobj2sha1
gitHJN commit-tree $(<treeobj2sha1) -p $(<commit1Sha1) -m 'second commit' > commit2Sha1
print "gitHJN log"
print $(<commit2Sha1)
print " > log"
gitHJN log $(<commit2Sha1) > log
echo -e show log :
cat log
echo -e

# print "test update-ref"
# gitHJN update-ref refs/heads/master $(<commit2Sha1)
# gitHJN log master

# print "test symbolic-ref"
# gitHJN update-ref refs/heads/test $(<commit1Sha1)
# gitHJN symbolic-ref HEAD refs/heads/test
# gitHJN log

# print "test commit"
# gitHJN commit -m 'test commit'
# gitHJN log