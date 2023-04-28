#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>

using namespace std;

bool Compare(pair<int,int>&a,pair<int,int>&b){
	if(a.second!=b.second){
		return a.second<b.second;
	}
	else{
		return a.first<b.first;
	}
}
int main(){
	int N;
	while(cin>>N){
		unordered_map(string, vector<pair<int,int>>) fmap;
		fmap.clear();
		for(int i=0;i<N;i++){
			string name="";
			int id,weight;
			cin>>name>>id>>weight;
			fmap[name].push_back(pair<int,int>(id,weight));
		}
		for(auto it=fmap.begin();it!=fmap.end();it++){
			sort(it->second.begin(),it->second.end(),Compare);
			for(int i=0;i<it->second.size();i++){
				cout<<it->first<<' '<<it->second[i].first<<' '<<it->second[i].second<<endl;
			}
		}
	}
	return 0;
}