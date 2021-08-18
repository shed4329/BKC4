## 功能描述
支持简单的运算，更换计算器的外观

## 需要
jdk8及以上

## 截图
[![foaJQf.png](https://z3.ax1x.com/2021/08/18/foaJQf.png)](https://imgtu.com/i/foaJQf)

## Q&A
### cmd窗口用javac编译时提示“编码GBK的不可映射字符”
[![foUeEj.png](https://z3.ax1x.com/2021/08/18/foUeEj.png)](https://imgtu.com/i/foUeEj)

#### 解决方法一：
>javac -encoding UTF-8 BKC4beta.java

使用以上代码进行编译

#### 解决方法二：
用记事本打开文件，点击 文件(F)-另存为(A)，将编码改为ANSI
[![foa2mF.png](https://z3.ax1x.com/2021/08/18/foa2mF.png)](https://imgtu.com/i/foa2mF)

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
