<p align="center">
	<img src="http://res1.sikomc.xyz/hbg01.png" style="width: 1200px"></img>
</p>
<h1>最新内部版-HuneiandEngine-SP0.0.1</h1>

>脚本功能和UDP功能均在开发中！在此版本中，只有UDP里的部分功能可用！我们会完成开发的！

>此版本为我们的第一个内部版，bug还是很多。请在issues中反馈！

>测试引擎是否安装成功：Python3.X运行以下代码： 若打开后，Minecraft聊天栏没有消息，则安装失败。
```#!/usr/bin/env python
import socket,base64,random,time
addr=('127.0.0.1',25560)
s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
def strToBase64(s):
    strEncode = base64.b64encode(s.encode('utf8'))
    return str(strEncode, encoding='utf8')
msg="#!!HUNEIAND#MSG#&PlayerChat@你的MC名称w==#"

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
while True:
    time.sleep(2) #如果您删掉这句休眠，这个程序还可以为您测压
    rn=random.randint(0,32676)
    text="Hello, Minecraft!"+str(rn)
    text=strToBase64(text)
    nmsg=msg+text
    s.sendto(nmsg.encode("utf8"),addr)
    print(nmsg)
s.close()
