# 3道主观题

1、C语言，把某个字符变成大写。例如：aabbccddee 改为 aabbccDDee

```
#include <stdio.h>

void upperTheLetter(char[] letters, char target)
{
	for (int i = 0 ; i < letters.length; i++)
	{
		if (letters[i] == target) {
			letters[i] -= 32;
		}
	}
}
```

2、删除List中的某个节点


struct ListNode {

    int value;

    struct ListNode* next;

}


```C++
#include <iostream>
#include <stdlib>

bool delete(ListNode *&head, ListNode *p)

{
	if (head == nullptr || p == nullptr) return false;
	// 默认该链表有虚拟头节点
	ListNode *pre = head;
	ListNode *cur = head->next;
	while (cur != nullptr) {
		if (cur == p) {
			pre->next = cur->next;
			return true;
		}
	}
	return false;
}
```


3、找到字符串中的特定字符串"sonoscape"，统计其出现次数

```
#include <string>
#include <algorithm>

int countAppearTimes(string s) {
	string target = "sonoscape";
	// 1.s中的字符全部转为小写

	// 用string::find(s, target)函数找，每次找到位置往后移"sonoscape"的长度，统计次数
}
```
