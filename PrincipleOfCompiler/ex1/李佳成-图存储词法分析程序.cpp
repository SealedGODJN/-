#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <vector>
#include<unordered_map>
using namespace std;
class State{
public:
	//id请从1开始，0具有特殊含义
	int id;
	unordered_map<char,int> nxt;
	// map<char,bool> mp;
	bool isend;
	//special = 0 全部不接受
	//special = 1 全部接受
	//special = 2 正常
	int special;
	int specialnext=-1;
}state[5];

void init(){
	state[0].id = 1;
	for(int i=0;i<26;i++){
		state[0].nxt[char('a'+i)]=1;
		state[0].nxt[char('A'+i)]=1;
	}
	state[0].isend = 0;
	state[0].special = 2;

	state[1].id = 2;
	for(int i=0;i<26;i++){
		state[1].nxt[char('a'+i)]=1;
		state[1].nxt[char('A'+i)]=1;
	}
	for(int i=0;i<=9;i++){
		state[1].nxt[char('0'+i)]=1;
	}
	state[1].isend = 0;
	state[1].special = 1;
	state[1].specialnext = 2;

	state[2].id = 3;
	state[2].isend = 1;
	state[2].special = 0;
}
int getstring(const string &str,const int &startpos){
	int index = 0;
	int i = startpos;
	while(i<str.size()){
		if(state[index].special == 0){
			if(state[index].isend == 1){
				return i-1;
			} else {
				return -1;
			}
		}else if(state[index].special == 1){
			int nxt = state[index].nxt[str[i]];
			// cout<<"nxt: "<<nxt<<endl;
			if(nxt == 0){
				index = state[index].specialnext;
				// cout<<"index: "<<index<<endl;
			} else {
				index = nxt;
			}
		}else if(state[index].special == 2){
			int nxt = state[index].nxt[str[i]];
			// cout<<"nxt: "<<nxt<<endl;
			if(nxt == 0){
				if(state[index].isend == 1){
					return i-1;
				}
				return -1;
			} else {
				index = nxt;
			}
		}
		i++;
	}
	if(state[index].isend == 1){
		return i-1;
	} else {
		return -1;
	}
}
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	init();
	string str;
	getline(cin,str);
	str = str + "\n";
	// cout<<str.size()<<endl;
	int startpos = 0;
	while(startpos<str.size()){
		int finishpos = getstring(str, startpos);
		// cout<<finishpos<<endl;
		if(finishpos == -1){
			startpos++;
		} else {
			for(int i = startpos; i <= finishpos; i++){
				cout<<str[i];
			}
			cout<<endl;
			startpos = finishpos + 1;
		}
	}
	return 0;
}
