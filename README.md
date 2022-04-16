<p align="center">
	<img src="http://res1.sikomc.xyz/hbg01.png" style="width: 1200px"></img>
</p>
<h1>HuneiandEngine-V1.0.0</h1>

>此版本为我们的第一个正式版，bug还是很多。请反馈到邮箱18623304101@163.com 或者 ZRY551@zry551.cn

>测试引擎是否安装成功：Python3.X运行以下代码：
```#!/usr/bin/env python
import socket,base64,random,time
addr=('127.0.0.1',25560)
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
def strToBase64(s):
    strEncode = base64.b64encode(s.encode('utf8'))
    return str(strEncode, encoding='utf8')
msg="#!!HUNEIAND#MSG#&PlayerChat@TUNfWlJZXzE2Mw==#"

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
while True:
    time.sleep(0.01) 
    rn=random.randint(0,32676)
    text="Hello, Minecraft!"+str(rn)
    text=strToBase64(text)
    nmsg=msg+text
    s.sendto(nmsg.encode("utf8"),addr)
    print(nmsg)
s.close()
