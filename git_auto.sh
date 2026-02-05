#!/bin/bash

# الانتقال إلى المسار المحدد
cd /storage/emulated/0/Alarms/test_app/ || { echo "فشل في الوصول للمسار"; exit 1; }

# انتظار الإدخال من المستخدم (مثلاً، اضغط Enter لبدء)
echo "prees Enter for contenu..."
read -p "أدخل رسالة الالتزام (commit message): " commit_message

# تنفيذ أوامر Git
git add .
git commit -m "$commit_message"
git push origin main  # أو الفرع الذي تستخدمه

