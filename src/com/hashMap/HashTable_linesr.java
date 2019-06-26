package com.hashMap;

/**
 * @Description: 基于线性探测
 * @Author CLD
 * @Date 2019/6/26 15:36
 */

// 默认key为int>0,value为String
public class HashTable_linesr {

    int[] keys;
    String[] values;
    int N=0;//键值对总数
    int M=16;//散列表大小

    public HashTable_linesr(){
        keys = new int[M];
        values = new String[M];
    }
    public int hash(int key){
        return key % M;
    }

    public void put(int key,String value){
        int index=hash(key);
        // 为了方便假设key都大于0
        while(keys[index]!=0){
            if(keys[index]==key){
                values[index]=value;
                return;
            }
            index = (index+1)%M;
        }
        keys[index]=key;
        values[index]=value;
        N++;
        if(N>=M/2) resize(M*2);//方法散列表
    }

    public String get(int key){
        int index=hash(key);
        while(keys[index]!=0){
            if(keys[index]==key){
                return values[index];
            }
            index = (1+index)%M;
        }
        return null;
    }

    // 注意需要将整个键簇的值都重插一遍！
    public void remove(int key){
        int index = hash(key);
        while(keys[index]!=0 && keys[index]!=key)
            index = (index+1)%M;
        if(keys[index]!=key) return;
        keys[index]=0;
        values[index]=null;
        index = (index+1)%M;
        while(keys[index]!=0){
            int curKey = keys[index];
            String curValue = values[index];
            keys[index]=0;
            values[index]=null;
            N--;
            put(curKey,curValue);
            index = (index+1)%M;
        }
        N--;
        if(N<=M/8) resize(M/2);
    }

    // 重新装填
    public void resize(int M){
        this.M = M;
        int[] old_keys = keys;
        String[] old_values = values;
        keys = new int[M];
        values = new String[M];
        for(int i=0;i<old_keys.length;i++){
            if(old_keys[i]!=0)
                put(old_keys[i],old_values[i]);
        }
    }

}
