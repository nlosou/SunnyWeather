#!/bin/bash
#Noser
# 进入项目目录
cd "$(dirname "$0")"
# 添加所有更改
git add .
# 提交更改，使用当前日期作为提交信息
#git commit -m "增加下拉刷新"
git commit -m "Auto commit on $(date)"
# 推送更改
git push origin main