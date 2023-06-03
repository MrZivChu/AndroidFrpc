# 简介

[frp](https://github.com/fatedier/frp) 在android 上的实现，在[frpc-Android](https://github.com/FrpcCluster/frpc-Android) 代码做了简化，同时更新frpc jni 库到最新。

# 1. 使用方法

1. 参考https://gofrp.org/docs/ 按照UI提示填写你的配置文件点击后运行；
2. 见【进阶使用】 章节。

# 2. Sample

如下是一个示例用以远程adb:

1. frpc.ini 中内容：

   ```ini
   [common]
   server_addr = aaa.bbb.net
   server_port = 23
   token = password
   
   [adb]
   type = tcp
   local_ip = 127.0.0.1
   local_port = 5555
   remote_port = 35555
   ```

3. 依赖WIFI/ETH adb 正常运行,启用如下：

   ```shell
   adb tcpip 5555

3. 按照UI 提示填写你的配置；

4. 点击apk 主页中start frpc 运行即可

# 3. 进阶使用(since v2.0)

使用解包修改frpc.ini文件并重新签名安装使用

```shell
unzip app-release.apk -d app_modify
vi app_modify/assets/frpc.ini     // 进行必要的修改
cd app_modify
zip -r -0 ../modify.apk *
cd ..
zipalign -p -f -v 4 modify.apk modify_4k.apk // 进行4k对齐
java -jar /usr/bin/apksigner  sign --ks /mnt/d/Android/MyAndroidKey1.jks --v2-signing-enabled true --ks-key-alias key0 --out modify_4k_signed.apk modify_4k.apk          //ks路径 以你自个的ks 为准
adb install -r -g modify_4k_signed.apk    //安装完之后即可享用
```