#!/bin/bash

# 1.打印当前工作路径
user_path=`pwd`/
echo Current Working Path: $user_path

# 2.遍历当前文件夹
for file in `ls`
do
     if [ -f $user_path$file ]
     then
         echo Operation File: $user_path$file
         # scp $user_path$file $1:$2
     else
         echo File Not Found: $user_path$file
     fi
done
