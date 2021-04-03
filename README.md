# 批量发送钓鱼邮箱
## 场景介绍
在红队进行渗透期间,社工钓鱼的手段必不可少。但由于收集到的邮箱数量众多,单个发送影响时间。此工具可以批量发送邮箱,以达到"大面积撒网"的目的。
## 作者微信</br>
![mmqrcode1615447892452](https://user-images.githubusercontent.com/52184829/110867567-ef6c7300-8301-11eb-8fb9-a55274c820c9.png)
有问题即使添加好友进行反馈
## 目录结果
1.文件为源码
2.jar文件为编译好的文件，可以直接运行
## 使用说明
### 1.java -jar sendMail.jar
![image](https://user-images.githubusercontent.com/52184829/113479806-af914980-94c3-11eb-99e2-b86499b081e0.png)
### 2.模板地址为txt文件
![image](https://user-images.githubusercontent.com/52184829/113479844-dcddf780-94c3-11eb-9705-97fdeb1c60d2.png)
模板第一行为主题 </br>
第二行开始其余内容为邮件正文,支持html模板
![image](https://user-images.githubusercontent.com/52184829/113479854-e8312300-94c3-11eb-8924-26e01f12ab32.png)
### 3.收件人地址为txt文件
![image](https://user-images.githubusercontent.com/52184829/113479941-54138b80-94c4-11eb-8838-643280063417.png)
![image](https://user-images.githubusercontent.com/52184829/113479960-71485a00-94c4-11eb-9439-5406cd070eab.png)
### 4.附件地址为任意文件
![image](https://user-images.githubusercontent.com/52184829/113479989-9b018100-94c4-11eb-983a-d62680bbb904.png)
![image](https://user-images.githubusercontent.com/52184829/113480011-bec4c700-94c4-11eb-8176-d56560eff7c9.png)
### 5.发件人邮箱为你的邮箱地址 例如:xxxxxxx@qq.com
### 6.发件人密钥为使用的邮件服务器提供的api密钥
不会获取请查看此文章https://service.mail.qq.com/cgi-bin/help?subtype=1&&no=1001256&&id=28
### 7.邮箱服务器为smtp.qq.com、smtp.163.com，其他请自行百度
## 效果如图
![image](https://user-images.githubusercontent.com/52184829/113480128-64783600-94c5-11eb-83b4-676a340bfa42.png)
![image](https://user-images.githubusercontent.com/52184829/113480221-dc466080-94c5-11eb-9c1e-04170f075fbc.png)




