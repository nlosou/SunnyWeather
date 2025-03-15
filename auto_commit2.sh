#!/bin/bash
# 进入项目目录
cd "$(dirname "$0")"


# 定义新分支名称（需替换为你的目标分支名）
NEW_BRANCH="ViewModel_Optimize"

# 创建并切换到新分支（本地）
#git checkout -b $NEW_BRANCH

# 添加所有更改
git add .

# 提交更改（使用日期作为提交信息）
git commit -m "Auto commit on $(date)"

# 推送并关联远程分支（自动创建远程分支）
git push -u origin $NEW_BRANCH