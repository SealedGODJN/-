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
    cin >> N;
    vector<Fruits> ft;
    Fruits fft;
    while(cin >> fft.name >> fft.id >> fft.weight){
        ft.push_back(fft);
    }
    for (auto it = ft.begin(); it != ft.end(); it++)
    {
        cout << it->name << ' ' << it->id << ' ' << it->weight << endl;
    }
    sort(ft.begin(),ft.end(),Compare);
    for(auto it=ft.begin();it!=ft.end();it++){
        cout<<it->name<<' '<<it->id<<' '<<it->weight<<endl;
    }
    return 0;
}