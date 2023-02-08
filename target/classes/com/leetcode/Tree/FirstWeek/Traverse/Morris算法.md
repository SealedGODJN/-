二叉树的遍历我们已经介绍过了最常见的递归遍历：用Java描述数据结构之二叉树，前序遍历，中序遍历，后序遍历
和迭代遍历二叉树前序，中序，后序遍历的迭代实现，实现思路及代码

上面两种遍历虽然看上去不同，但是底层的思路是大同小异的，都是调用栈，递归是用java虚拟机栈，而迭代使用我们自己创建的栈。今天要介绍的莫里斯算法，它的背后没有用任何栈相关的知识，而且相比于前两种空间复杂度为O(n)（因为进栈最差会所有数据全部进栈）的遍历来说，莫里斯算法的空间复杂度只有O(1)，这对于量很大的数据来说无疑是天上地下的区别。

## Morris遍历

先来简单介绍一下莫里斯算法：

这种方法由 J. H. Morris 在 1979 年的论文「Traversing Binary Trees Simply and Cheaply」中首次提出，因此被称为 Morris 遍历。
介绍就这么一句话，百度百科也没有关于莫里斯算法的介绍，这句话还是复制于力扣。

## Morris遍历核心思想

通过递归和迭代遍历二叉树的核心是==回溯==，通过==栈==去回溯已经经过的节点，比如左子树遍历完了，回溯到根节点去遍历右子树，而莫里斯算法对于回溯的实现采用了一种特殊的办法，拿下面这颗树举例：


当把这棵树的根节点传入莫里斯遍历方法中时，cur 指向根节点，算法能回溯的关键是他会创建一个指向next = cur.left（为了将核心思路暂且认为存在left），然后==沿着cur.left一路往最右边走next = next.right==

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553968832034.png)

当走到最右面时让==next.right == cur==

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553968437032.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553967657030.png)

这是实现了莫里斯算法回溯的核心，很多人有疑惑为什么是最右边，此时我们把视野放大一点去看

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553966953528.png)



A的左子树它的最右面是H，对于A的左子树的遍历的最后一个节点是H，就是说 cur 遍历完H之后就该回到根节点A了，此时让 cur = cur.right不就完成了回溯，成功回到A了嘛。对于看这篇博客的同学相信已经能理解递归和迭代遍历了，那么也应该能理解我刚刚说的这个是可以应用于递归特性的，即A的左子树的左子树，A的右子树，A的右子树的左子树都适用上面的思想。

介绍完了核心思想，我们来看莫里斯算法的具体代码（注意下面代码中的注释对理解算法也很重要请仔细看）：

Morris遍历核心代码

```java
 public static void morris(TreeNode root){
        //进入方法让cur = root
        TreeNode cur = root;
        //没有遍历完之前持续循环
        while (cur != null){
            /*
            对于迭代还是递归，一个节点的左子树即使为空
            它们都会让cur = left 然后回溯回来
            morris这里就会判断如果一个节点的左边为空就不会去
            */
            if (cur.left == null){
                cur = cur.right;
            }else {
                /*
                让next = cur.left，然后一直往左子树的最右面走
                （上文已经说明了为什么要这么做）
                */
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur)
                    next = next.right;
                /*
                此时next经过上面while循环的
                next = next.right之后
                已经走到了左子树的最右边，我们讨论过此时要让
                next.right = cur
                以便cur走到这里时可以回溯回去，
                这件事是if(next.right == null)时
                要做的，else中做的是cur回溯回去了，
                但是下一次经历最外面的while循环
                时他还会创建一个next = cur.left的引用
                然后一直遍历到cur左子树的最右面
                这次是第二次经过这里我们知道
                next.right != null 而是next.right == cur
                为了保证原树不变我们要把这条线擦掉，
                即next.right = null，然后让cur = cur.right
                即cur的左子树走完了，让cur去走右子树。
                 */
                if (next.right == null){
                    next.right = cur;
                    cur = cur.left;
                }else {
                    System.out.print((char)cur.val + " ");
                    cur = cur.right;
                    next.right = null;
                }
            }
        }
    }

```

## Morris遍历过程

以上就是莫里斯算法的核心遍历代码，为了方便大家能更加深刻的理解，我们来就例子中的树走一遍该算法：

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553889722414.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553890395316.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553891282618.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553892089720.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553892791922.png)



![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553895340724.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553896182326.png)



在感觉还没有理解的情况下请多走两边上图过程，对着代码走最佳。

在介绍了莫里斯算法的核心代码之后，我们来继续谈通过莫里斯算法实现前中后序遍历。对于递归和迭代实现的前中后序遍历，从代码层面来看我们很容易理解，拿递归来说

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553887294512.png)

进入之后，在去遍历root.left 和root.right 之前先对节点处理就是先序遍历，相应的在遍历完左子树再来处理节点就是就是中序遍历，遍历完右子树最后处理根节点就是后续遍历，无非还是下图中第一次经过节点就是先序，第二次就是中序，第三次就是后序：

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-164553886371110.png)

但是对于莫里斯算法，代码层面很难看出来什么时候是第一次，什么时候是第二次，什么时候是第三次。这个时候我们去看上面那个流程图里cur指针的具体走向，可以发现，cur的路径如下:

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-16455383248292.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-16455383327304.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-16455383427676.png)

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70-16455383535738.png)



### 总结

仔细观察就会发现上图cur 所遍历的所有节点中可以分为两类
==第一类==是有左子树的节点：A B C
==第二类==是没有左子树的节点：D E H F G

对于第一类节点，cur能经过两遍，第一遍是第一次经过的时候，第二遍是回溯的时候，而对于第二类没有左子树的节点不存在往左走然后回溯的问题（递归和迭代中这类节点还是会往左走在回溯，第一次经过时处理是先序遍历，从左回溯回来第二次经过时处理是中序遍历），所以就先序遍历和中序遍历而言第二类节点不需要经过两次，因为第一次经过时处理它在总顺序中的位置和在去它为空的左边回溯回来再处理它在总顺序中的位置是一样的，所以对于先序遍历和中序遍历而言，只是什么时候处理第一类节点。画图表现就是这样：

![在这里插入图片描述](Morris%E7%AE%97%E6%B3%95.assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ2MjA5ODQ1,size_16,color_FFFFFF,t_70.png)

在图中我们可以更加清楚地看到先序遍历和中序遍历第二类节点的相对位置还不发生变化的。

从上面的描述中可以看到有第一遍经过和第二遍经过，但是没有第三遍经过，也就是说后序遍历现有代码还没办法实现，需要改造一下，先把先序遍历和中序遍历讲完。

## 三种遍历

### 先序遍历

先序遍历需要在第一次经过的时候就处理第一类节点，下面是代码：

```java
public static void morrisPreoder(TreeNode root){
    TreeNode cur = root;
    while (cur != null){
        //遇到第二类节点直接在这处理只经过一次
        if (cur.left == null){
            System.out.print((char)cur.val + " ");
            cur = cur.right;
        }else {
            TreeNode next = cur.left;
            while (next.right != null && next.right != cur){
                next = next.right;
            }
            if (next.right == null){
                next.right = cur;
                //此时是第一次经过第一类节点
                System.out.print((char)cur.val + " ");
                cur = cur.left;
            }else {
                next.right = null;
                cur = cur.right;
            }
        }
    }
}

```



```java
public class MorrisPreorder {
	//构建例子中的树
    public static TreeNode buildTree() {
		TreeNode a = new TreeNode('a');
        TreeNode b = new TreeNode('b');
        TreeNode c = new TreeNode('c');
        TreeNode d = new TreeNode('d');
        TreeNode e = new TreeNode('e');
        TreeNode f = new TreeNode('f');
        TreeNode g = new TreeNode('g');
        TreeNode h = new TreeNode('h');

        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        e.right = h;

        return a;
    }
    public static void morrisPreoder(TreeNode root){
        TreeNode cur = root;
        while (cur != null){

            if (cur.left == null){
                System.out.print((char)cur.val + " ");
                cur = cur.right;
            }else {
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur){
                    next = next.right;
                }
                if (next.right == null){
                    next.right = cur;
                    //System.out.print((char)cur.val + " ");
                    cur = cur.left;
                }else {
                    next.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();
        morrisPreoder(root);
    }
}
```




### 中序遍历



```java
public class MorrisInorder {
    public static TreeNode buildTree() {    
        TreeNode a = new TreeNode('a');
        TreeNode b = new TreeNode('b');
        TreeNode c = new TreeNode('c');
        TreeNode d = new TreeNode('d');
        TreeNode e = new TreeNode('e');
        TreeNode f = new TreeNode('f');
        TreeNode g = new TreeNode('g');
        TreeNode h = new TreeNode('h');

        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        e.right = h;

        return a;
    }

    public static void morrisInorder(TreeNode root){
        TreeNode cur = root;
        while (cur != null){

            if (cur.left == null){
                //第二类节点位置不变
                System.out.print((char)cur.val + " ");
                cur = cur.right;
            }else {
                TreeNode next = cur.left;
                while (next.right != null && next.right != cur){
                    next = next.right;
                }
                if (next.right == null){
                    next.right = cur;
                    cur = cur.left;
                }else {
                    //第二次经过第一类节点时处理第一类节点
                    System.out.print((char)cur.val + " ");
                    next.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildTree();
        morrisInorder(root);
    }

```


### 后序遍历

由于递归等版本的遍历二叉树后序遍历是在第三次到达节点的时候打印本次节点，但是Morris遍历只能到达一个最多节点两次。所以不能在代码层面不能直接后序遍历，只能为了得到后序遍历的顺序做一些改变

    public static List<Character> morrisPostOrder(TreeNode root) {
        List<Character> res = new ArrayList<>();
        if(root == null)
            return res;
        TreeNode node = new TreeNode(-1);  //建立临时节点
        node.left = root;    //设置临时节点的左子节点为根节点
        TreeNode cur = node;
        while(cur != null) {
            if(cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode next = cur.left;
                while(next.right != null && next.right != cur)
                    next = next.right;
                if(next.right == null) {
                    next.right = cur;
                    cur = cur.left;
                } else {
                    next.right = null;
                    TreeNode t = cur.left;
                    List<Character> midList = new ArrayList<>();
                    while(t != null) {
                        System.out.println();
                        //注意这里不是尾插，是插到0的位置上
                        midList.add(0, (char) t.val);
                        t = t.right;
                    }
                    res.addAll(midList);
                    cur = cur.right;
                }
            }
        }
        //最终得到了后序遍历顺序的顺序表
        return res;
    }
