#include<iostream>
#include<vector>
#include<string>
#include<algorithm>

using namespace std;

typedef struct Fruits{
	string name;
	int id;
	int weight;
}Fruits;

bool Compare(Fruits&a,Fruits&b){
	if(a.name!=b.name){
		return a.name<b.name;
	}
	else if(a.weight!=b.weight){
		return a.weight<b.weight;
	}
	else{
		return a.id<b.id;
	}
}
int main(){
	int N;
	while(cin>>N){
		vector<Fruits> ft;
		ft.clear();
		for(int i=0;i<N;i++){
			string tmname="";
			int iid,wweight;
			cin>>tmname>>iid>>wweight;
			Fruits fft;
			fft.name=tmname;
			fft.id=iid;
			fft.weight=wweight;
			ft.push_back(fft);
		}
		sort(ft.begin(),ft.end(),Compare);
		for(auto it=ft.begin();it!=ft.end();it++){
			cout<<it->name<<' '<<it->id<<' '<<it->weight<<endl;
		}
	}
	return 0;
}
