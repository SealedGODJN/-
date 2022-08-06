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

testdir="git-test"
if [ -e "$testdir" ]; then
    rm -rf $testdir
fi

mkdir $testdir
cd $testdir

print "test init"
gitHJN init junDir
git init gitDir
echo -e

print "test hash-object"
echo "version1" > junDir/file.txt
echo "version1" > gitDir/file.txt
gitHJN hash-object -w junDir/file.txt > hash1
git hash-object -w gitDir/file.txt > hash2
cmp hash1 hash2
echo -e

print "test cat-file"
h1=$(<hash1)
h2=$(<hash2)
gitHJN cat-file -p $h1 > fileContent1
git cat-file -p $h2 > fileContent2
cmp fileContent1 fileContent2

gitHJN cat-file -t $h1 > fileType1
git cat-file -t $h2 > fileType2
cmp fileType1 fileType2
gitHJN cat-file -s $h1 > fileSize1
git cat-file -s $h2 > fileSize2
cmp fileSize1 fileSize2
echo -e

print "test update-index --add, git ls-files --stage"
cd junDir
gitHJN update-index --add file.txt
gitHJN ls-files --stage > ../stage1
cd ../gitDir
git update-index --add file.txt
git ls-files --stage > ../stage2
cd ..
cmp stage1 stage2
echo -e

print "test write-tree"
cd junDir
mkdir dir
echo 123 > dir/file1
echo abc > dir/file2
gitHJN update-index --add dir/file1
gitHJN update-index --add dir/file2
gitHJN write-tree > treeobj1sha1
#mkdir ../../test-data
#gitHJN cat-file -p $(<treeobj1) > ../../test-data/test-write-tree-data.txt
gitHJN cat-file -p $(<treeobj1sha1) > treeobjContent
cmp treeobjContent ../../test-data/test-write-tree-data.txt
echo -e

print "test commit-tree, log"
print "gitHJN commit-tree $(<treeobj1sha1) -m 'first commit' > commit1Sha1"
gitHJN commit-tree $(<treeobj1sha1) -m "first commit" > commit1Sha1
print "echo version2 > file.txt"
echo version2 > file.txt
print "gitHJN update-index --add file.txt"
gitHJN update-index --add file.txt
print "gitHJN write-tree > treeobj2sha1"
gitHJN write-tree > treeobj2sha1
print "gitHJN commit-tree $(<treeobj2sha1) -p $(<commit1Sha1) -m "second commit" > commit2Sha1"
gitHJN commit-tree $(<treeobj2sha1) -p $(<commit1Sha1) -m "second commit" > commit2Sha1
print "gitHJN log $(<commit2Sha1) > log"
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