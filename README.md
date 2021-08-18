## 功能描述
支持简单的运算，更换计算器的外观

## 版本支持
jdk8,11,14,15,16都可运行

## 截图
Windows下
![这是图片](https://imgpp.com/images/2021/08/17/QQ20210818104407.png"Windows下")

## Q&A
### cmd窗口用javac编译时提示“编码GBK的不可映射字符”
![这是图片](https://imgpp.com/images/2021/08/17/QQ20210817221113.png"编码GBK的不可映射字符")

#### 解决方法一：
>javac -encoding UTF-8 BKC4beta.java

使用以上代码进行编译

#### 解决方法二：
用记事本打开文件，点击 文件(F)-另存为(A)，将编码改为ANSI
![这是图片](https://imgpp.com/images/2021/08/17/QQ20210817223305.png"另存为")

### 标题为默认图片
请将32.png放置于同级目录下

### "关于"没有图标
请将32.png放置于同级目录下

### 在IntelliJ IDEA 内添加了图标但没有效果
#### 标题栏图标
将第31行的
>frame.setIconImage(new ImageIcon("32.png").getImage());

改为

>frame.setIconImage(new ImageIcon("src\\32.png").getImage());

#### "关于"图标
将第56行的
>about.addActionListener(e -> JOptionPane.showMessageDialog(null, "BKC4 beta 4.01", "关于", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("32.png")));

改为

> about.addActionListener(e -> JOptionPane.showMessageDialog(null, "BKC4 beta 4.01", "关于", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src\\32.png")));

### 在没有jdk的电脑上运行
运行BKC4beta.exe
