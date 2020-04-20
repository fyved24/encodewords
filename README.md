# EncodeWords

这是一个奇怪的东西，它可以将你的任何话用自定义的文字加密（不算是加密，最多是转码）

## 原因

无聊的时候在网上看到感觉挺好玩的就自己做了一个

## 怎么使用

运行起来以后，发出请求即可，负载是json格式



### 加密

> /aowu/encode

keys: 即是用来加密的关键字

value: 需要加密的内容

![image-20200420175128026](https://img.ironz.cn/img/image-20200420175128026.png)

### 解密

> /aowu/decode

keys: 即是用来加密的关键字

value: 需要解密的内容

![image-20200420175320496](https://img.ironz.cn/img/image-20200420175320496.png)

## 注意事项

- 关键字只支持2^n个最多16个

  > 所以说 支持的关键字数量就是 2, 4, 8, 16 (ε=ε=ε=┏(゜ロ゜;)┛

- 关键字的长度需要一样

  > 比如 **爱国,敬业,诚信,友善**这四个关键字的长度就是一样的，原就是懒，实现起来简单
  

## 未来打算

- 做命令行模式