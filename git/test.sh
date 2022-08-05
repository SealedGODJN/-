#!/usr/bin/env bash

function print() {
    line=$(caller)
    echo $line ":" $@
}

go build
run=$(realpath ./gitHJN)
function run(){
  $run $@
}

testdir="../git-test"
if [ -e "$testdir" ]; then
    rm -rf $testdir
fi

mkdir $testdir
cd $testdir

print "test git init"
run init runDir
git init gitDir
echo -e

print "test git hash-object"
echo "version1" > runDir/file.txt
echo "version1" > gitDir/file.txt
run hash-object -w runDir/file.txt > hash1
git hash-object -w gitDir/file.txt > hash2
cmp hash1 hash2
echo -e

print "test git cat-file"
h1=$(<hash1)
h2=$(<hash2)
run cat-file -p $h1 > fileContent1
git cat-file -p $h2 > fileContent2
cmp fileContent1 fileContent2
run cat-file -t $h1 > fileType1
git cat-file -t $h2 > fileType2
cmp fileType1 fileType2
run cat-file -s $h1 > fileSize1
git cat-file -s $h2 > fileSize2
cmp fileSize1 fileSize2
echo -e

print "test git update-index --add, git ls-files --stage"
cd runDir
run update-index --add file.txt
run ls-files --stage > ../stage1
cd ../gitDir
git update-index --add file.txt
git ls-files --stage > ../stage2
cd ..
cmp stage1 stage2
echo -e

print "test git write-tree"
cd runDir
mkdir dir
echo 123 > dir/file1
echo abc > dir/file2
run update-index --add dir/file1
run update-index --add dir/file2
run write-tree > treeobj1sha1
#mkdir ../../test-data
#run cat-file -p $(<treeobj1) > ../../test-data/test-write-tree-data.txt
run cat-file -p $(<treeobj1sha1) > treeobjContent
cmp treeobjContent ../../test-data/test-write-tree-data.txt
echo -e

print "test commit-tree, log"
run commit-tree $(<treeobj1sha1) -m 'first commit' > commit1Sha1
echo version2 > file.txt
run update-index --add file.txt
run write-tree > treeobj2sha1
run commit-tree $(<treeobj2sha1) -p $(<commit1Sh1) -m 'second commit' > commit2Sha1
run log $(<commit2Sha1) > log
echo -e show log :
cat log
echo -e

print "test update-ref"
run update-ref refs/heads/master $(<commit2Sha1)
run log master

print "test symbolic-ref"
run update-ref refs/heads/test $(<commit1Sha1)
run symbolic-ref HEAD refs/heads/test
run log

print "test commit"
run commit -m 'test commit'
run log