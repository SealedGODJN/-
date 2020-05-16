package com.hjn.test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  *运行要求：  * 1、一行只能输入一个句子，且不能拖延到第二行，当然可以加多个或语句  * 2、推导符号，只能是→和->中一种，每行语句中必须有推导符
 *  * 3、如果某非终结符的是次大写字母表的某个字符，那么在后面只能用’或者'来表示  * 4、最后用end结束  *实现功能：  * 1、标准文法输入
 *  * 2、求First集和First求解算法  * 3、求Follow集合Follow集求解算法  * 4、随便多个语句输入  * 5、忽略空格的影响
 *  * 6、兼容两种推导符的输入和中英文输入一撇符和或连接符  * 7、简单输入错误判断功能  *某一输入实例：  * E->TE'  * E'->+E|ε
 *  * T->FT'  * T'->T|ε  * F->PF'  * F'->*F'|ε  * P->(E)|a|b|^  * end  
 */
public class FirstFollow3 {
	public ArrayList<String[]> in = new ArrayList<String[]>();// 这数据结构真是逼人绝路才去想到绝处逢生，哈哈，关键实现了可变长度文法接收，在这存放的是拆分后最简单的文法，也是由用户输入
	public ArrayList<String[]> first = new ArrayList<String[]>();// 包括左推导符和其First集
	public ArrayList<String[]> follow = new ArrayList<String[]>();
	public ArrayList<String[]> track = new ArrayList<String[]>();// track有一条一条的非终结符串组成的路径数组

	public FirstFollow3() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请分行输入一个完整文法:(end结束)");
		String sline = "";
		sline = sc.nextLine();
		while (!sline.startsWith("end")) {
			StringBuffer buffer = new StringBuffer(sline);
			int l = buffer.indexOf(" ");
			while (l >= 0) {// 去空格
				buffer.delete(l, l + 1);
				l = buffer.indexOf(" ");
			}
			sline = buffer.toString();
			String s[] = sline.split("->");// 左推导符
			if (s.length == 1)
				s = sline.split("→");// 考虑到输入习惯和形式问题
			if (s.length == 1)
				s = sline.split("=>");
			if (s.length == 1) {
				System.out.println("文法有误");
				System.exit(0);
			}
			StringTokenizer fx = new StringTokenizer(s[1], "|︱");// 按英文隔符拆开产生式或按中文隔符拆开
			while (fx.hasMoreTokens()) {
				String[] one = new String[2];// 对于一个语句只需保存两个数据就可以了，语句左部和语句右部的一个简单导出式，假如有或符，就按多条存放
				one[0] = s[0];// 头不变,0位置放非终结符
				one[1] = fx.nextToken();// 1位置放导出的产生式，就是产生式右部的一个最简单导出式
				in.add(one);
			}
			sline = sc.nextLine();
		}
//求First集过程
		this.process("First");
		/*
		 * 打印First集算法和First集
		 */
		System.out.println("\nFirst集算法：");
		this.print(track);// 打印First集算法
		System.out.println("\nFirst集：");
		for (int i = 0; i < first.size(); i++) {
			String[] r = first.get(i);
			System.out.print("First(" + r[0] + ")={");
			for (int j = 1; j < r.length; j++) {
				System.out.print(r[j]);
				if (j < r.length - 1)
					System.out.print(",");
			}
			System.out.println("}");
		}
		track.clear();// 因为下面还要用，这里就先清空了
//求Follow集过程
		this.process("Follow");
		System.out.println("\nFollow集算法：");
		for (int i = 0; i < track.size(); i++) {
			String[] one = track.get(i);
			System.out.print("Follow(" + follow.get(i)[0] + "):\t");
			for (int j = 0; j < one.length; j++)
				System.out.print(one[j] + "\t");
			System.out.println();
		}

		System.out.println("\nFollow集：");
		for (int i = 0; i < follow.size(); i++) {
			String[] r = follow.get(i);
			System.out.print("Follow(" + r[0] + ")={");
			for (int j = 1; j < r.length; j++) {
				System.out.print(r[j]);
				if (j < r.length - 1)
					System.out.print(",");
			}
			System.out.println("}");
		}
	}

	public void process(String firstORfollow){
		for(int i=0;i<in.size();i++){
			boolean bool=true;
			for(int j=0;j<i;j++)
				if(in.get(j)[0].equals(in.get(i)[0]))
					bool=false;
				if(bool){
					ArrayList<String> a=null;
					if(firstORfollow.equals("First"))
						a=this.getFirst(in.get(i)[0],"First("+in.get(i)[0]+")/");
					else if(firstORfollow.equals("Follow"))
						a=this.getFollow(in.get(i)[0],in.get(i)[0],"");
					
					String[] sf=new String[a.size()/2+1];
					String[] st=new String[a.size()/2];
					sf[0]=in.get(i)[0];
					for(int j=0;j<a.size();j++) {
						if(j%2==0)
							sf[j/2+1]=a.get(j);
						else
							st[j/2]=a.get(j);
					}
					if(firstORfollow.equals("First"))
						first.add(sf);//first集
					else if(firstORfollow.equals("Follow"))
						follow.add(sf);
					track.add(st);//对应上面求得集的路径，在开始保存该非终结符了，因为已保存了该字符的First或Follow表示法
			}
		}
	}

	public ArrayList<String> getFirst(String s, String track1) {// s表示左推导，track表示寻找路径，避免循环查找
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if (Character.isUpperCase(s.charAt(0))) {// 如果是非终结符，大写
			for (int i = 0; i < in.size(); i++) {
				String[] one = in.get(i);
				if (s.equals(one[0])) {
					if (track1.substring(0, track1.length() - 9).indexOf("First(" + s + ")") >= 0)// 假如在查找过程嵌套了这步，证明进入了无限循环，不需再找，此路径无结果
						;// 有点要注意一下，本来一开始就把第一个开始推导符的First路径放进去了的，所以要避开这一次，不然已开始就结束了
					else if (one[1].length() == 1 || one[1].charAt(1) != '\'' && one[1].charAt(1) != '’')
						result1 = getFirst(one[1].charAt(0) + "", track1 + "First(" + one[1].charAt(0) + ")/");
					else if (one[1].length() > 1 && one[1].charAt(1) == '\'' || one[1].charAt(1) == '’')// 假如接下来一个要求First的非终结符带了一撇，那一撇包括英文表示和中文表示
						result1 = this.getFirst(one[1].substring(0, 2),
								track1 + "First(" + one[1].substring(0, 2) + ")/");
					result = addArrayString(result, result1);
					result1.clear();
				}
			}
		} else {// 如果产生式首字符是终结字符
			if (s.equals("ε"))// 注意：表示空的字符只能是这种了，其他形式在这个编译器中不能通过，还请原谅
				result1.add("#");
			else
				result1.add(s);
			result1.add(track1);// 为了方便，把路径也加入了结果集，不然可能路径不匹配，没办法，因为中间有删去重复项
			result = result1;
		}
		return result;
	}

	public ArrayList<String> getFollow(String s,String element,String track1){//从右至左反推，不是求Follow的等价Follow，因为推到后面的反而范围大
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		if(Character.isUpperCase(s.charAt(0))){
			for(int i=0;i<in.size();i++){
				String[] one = in.get(i);
				int slen=s.length();
				int olen=one[1].length();
				if(element.equals(in.get(0)[0])){//如果是开始符号，或是可以反推到开始符号，证明也可以顺推导开始符号
					result1.add("#");
					result1.add(in.get(0)[0]+"→"+in.get(0)[0]+"\t");
					result = addArrayString(result,result1);
					result1.clear();
				}
				if(one[1].indexOf(s)>=0&&track1.indexOf((char)('a'+i)+"")>=0);
					//假如之前走过某一步，就不必再走了，那是死循环，之前在这语句前面加了个else,结果又部分内容显示不出来，总算发现了，就算反推到开始符号，也不一定就到结果了的，开始符号也可以反推，所以要继续
				else if(one[1].indexOf(s)>=0 && (olen-slen==one[1].indexOf(s)||slen==2||one[1].charAt(one[1].indexOf(s)+1)!='’'&&one[1].charAt(one[1].indexOf(s)+1)!='\'')) {
					//如果在右产生式中真正存在需要求反推的字符,后面的条件控制它是真正存在，因为里面包含这个字符也不一定是真，就像E’中包含E，但这不是真正的包含
					int index=-1;
					index = one[1].indexOf(s,0);
					while(index>=0){//之前这没有用到循环，结果可能少点东西，仔细一想，必须要，就算是一个推导语句，也可能推出多个相同非终结符的组合，其实这也是一种特殊情况了，不考虑也可能正确了，也可能之前在其他地方把这样的结果求出来了，不求也没事，但就像假如要求T的Follow集，假如可以产生出T+a*T*b，这时还是有用的，万一吧
						if(olen-slen==index){//如果该非终结符在末尾，那么求导出该产生式的非终结符的倒推
							result1=getFollow(one[0], element,track1+(char)('a'+i));
							result=addArrayString(result,result1);
							result1.clear();
						}else{//如果后继非终结符在产生式中不是最后
							int t=index+slen;//指向在产生式非终结符s的后一个字符位置
							result1=returnFirstofFollow(s, element, track1, one[0], one[1], index, t);
							result=addArrayString(result,result1);//之前也没写这句话，结果把之前的内容覆盖了，就是之前的数据丢失
							result1.clear();
						}
						index = one[1].indexOf(s,index+1);
					}//endwhile
				}
				if(one[1].endsWith(element)){//如果最开始要求的Follow集非终结符在末尾
					result1.add("#");
					result1.add(in.get(0)[0]+"→"+one[1]+"\t");
					result=addArrayString(result,result1);//之前也没写这句话，结果把之前的内容覆盖了，就是之前的数据丢失
					result1.clear();
				}
			}//endfor
		}
		return result;
	}

	public ArrayList<String> returnFirstofFollow(String s, String element, String track1, String one0, String one1,
			int index, int t) {// 返回求Follow集中要求的First集部分
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<String> beckFirst;
		String lsh;// 记录下一个字符
		if (t + 1 < one1.length() && (one1.charAt(t + 1) == '’' || one1.charAt(t + 1) == '\''))// 如果随后的非终结符还带了一撇符
			lsh = one1.substring(t, t + 2);
		else// 如果没带一撇,就只要截取一个字母就可以了
			lsh = one1.substring(t, t + 1);
		String[] ls = null;
		int beflen = 2;
		if (track1.length() > 0) {// 这些都是为了算法输出容易理解点用的，其实要不输出这算法，要省下好多东西
			ls = in.get((int) (track1.charAt(track1.length() - 1) - 'a'));// 得到上一步调用的语句
			if (Character.isUpperCase(ls[1].charAt(ls[1].length() - 1)))
				beflen = 1;
		}
		beckFirst = this.getFirst(lsh, "First(" + lsh + ")/");// 相当于得到后继字符的First集
		for (int j = 0; j < beckFirst.size() / 2; j++) {// 调用求First集，返回的不一定只一个结果
			String lh = "";
			if (beckFirst.get(j * 2).equals("#")) {
				result1.add(beckFirst.get(j * 2));// 这个加了是数据，下面一步就是把地址加上，就是一个结果，要两份数据
				if (ls == null)
					lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element + "ε"
							+ one1.substring(t + lsh.length(), one1.length());
				else
					lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
							+ one1.substring(index + s.length(), one1.length()) + "→." + element + "ε"
							+ one1.substring(t + lsh.length(), one1.length());
				result1.add(lh);
				result = addArrayString(result, result1);
				result1.clear();
				if (1 + index + lsh.length() < one1.length())// 证明后面还有字符,为什么要这一步，打个比方把，假如要求F的Follow集，而存在产生式FPQ,而P的有个First集为空，那么得还接着求Q的First，类推，假如最后一个字符Q还是返回空，那么求要求产生式左边的推导非终结符的Follow集了，必须把这些结果都算到F的Follow集中去
					result1 = returnFirstofFollow(s, element, track1, one0, one1, index, t + lsh.length());
				else// 到最后,那么求要求产生式左边的推导非终结符的Follow集了，其实这和上面一种情况都很特殊了，一般用不上了
					result1 = getFollow(one0, element, track1);
			} else {// 其实下面这一大坨都是为了易懂一点，Follow集算法清晰一点，好苦啊
				if (Character.isUpperCase(one1.charAt(t))) {// 如果是有随后的一个非终结符的First集求出的结果
					if (ls == null)
						lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element + beckFirst.get(j * 2)
								+ one1.substring(t + lsh.length(), one1.length());
					else
						lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
								+ one1.substring(index + s.length(), one1.length()) + "→." + element
								+ beckFirst.get(j * 2) + one1.substring(t + lsh.length(), one1.length());
				} else {// 如果不是大写，就是终结符了，那么用First集求出来的结果连接起来还是一样的，所以不要重复打印两次了
					if (ls == null) {
						if (element == in.get(0)[0] || s.equals(element))
							lh = in.get(0)[0] + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length()) + "\t";
						else
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length()) + "\t";
					} else {
						if (ls[1].length() == 1 || ls[1].length() == 2 && !ls[1].endsWith("’") && !ls[1].endsWith("\'"))
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + element
									+ one1.substring(t, one1.length());
						else
							lh = in.get(0)[0] + "→" + one1 + "→" + one1.substring(0, index) + ls[1]
									+ one1.substring(index + s.length(), one1.length()) + "→." + element
									+ one1.substring(t, one1.length()) + "!";
					}
				}
				result1.add(beckFirst.get(j * 2));// 这个加了是数据，下面一步就是把地址加上，就是一个结果，要两份数据
				result1.add(lh);
			}
		}
		result = addArrayString(result, result1);// 之前也没写这句话，结果把之前的内容覆盖了，就是之前的数据丢失
		result1.clear();
		return result;
	}

	public ArrayList<String> addArrayString(ArrayList<String> a, ArrayList<String> b) {// 两个字符串数组相加
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < a.size(); i += 2) {// 因为这每一个结果，都保存了两个数据，第一个是结果，第二个位置保存的是得到这结果的路径
			String s = a.get(i);
			if (result.contains(s) || s.equals("")) {// 如果结果集包含了这个字符串，就不加入结果集了，就是为了去掉重复项
				int index = result.indexOf(s);
				if (result.get(index + 1).length() > a.get(i + 1).length()) {// 如果新来的路径比现有的短
					result.set(index, s);
					result.set(index + 1, a.get(i + 1));
				}
				continue;
			}
			result.add(s);
			result.add(a.get(i + 1));// 还是要把路径继续保存在新的结果集中
		}
		for (int i = 0; i < b.size(); i += 2) {
			String s = b.get(i);
			if (result.contains(s) || s.equals("")) {
				int index = result.indexOf(s);
				if (result.get(index + 1).length() > b.get(i + 1).length()) {// 如果新来的路径比现有的短
					result.set(index, s);
					result.set(index + 1, b.get(i + 1));
				}
				continue;
			}
			result.add(s);// 偶数地址存放的是数据
			result.add(b.get(i + 1));// 奇数地址存放的是该数据获得的路径
		}
		return result;
	}

	public void print(ArrayList<String[]> list) {
		for (int i = 0; i < list.size(); i++) {// 循环非终结符个数次数
			String[] one = list.get(i);// 得到某一个非终结符运行的所有路径
			String[][] strings = new String[one.length][];
			String[] finals = new String[one.length];// 路径最终站点
			int number = 0;// 记录某一步最终有效站点个数，本来有几条路径，就因该有几个有效站点，但可能有些站点有重复的，即从同一站点发出
			int max = 0;
			for (int j = 0; j < one.length; j++) {
				strings[j] = one[j].split("/");
				if (strings[j].length > max)
					max = strings[j].length;// 求得某一非终结符路径最长一条
			}
			for (int j = 0; j < max; j++) {// 循环最长站点次数
				number = 0;
				for (int k = 0; k < strings.length; k++) {// 有多少条路径就循环多少次
					String lsh = "";
					if (j >= strings[k].length) {
						lsh = strings[k][strings[k].length - 1];
					} else {
						lsh = strings[k][j];
					}
					int m = 0;
					for (m = 0; m < number; m++) {// 记录有效站点
						if (lsh.equals(finals[m]))
							break;
					}
					if (m == number) {
						finals[number] = lsh;
						number++;
					}
				}
				for (int k = 0; k < number; k++) {// 打印每一条路径的某个站点
					System.out.print(finals[k]);
					if (k != number - 1)
						System.out.print(" + ");
				}
				if (j < max - 1)
					System.out.print(" = ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new FirstFollow3();
	}
}
