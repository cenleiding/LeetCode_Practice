package com.hashMap;

import java.util.HashMap;

/**
 * @Description: 基于链表法的简单实现
 * @Author CLD
 * @Date 2019/6/25 16:39
 */

// 节点类,默认key为int类型,value为String
class Entry{
    int key;
    String value;
    Entry next;
    public Entry(int key,String value){
        this.key=key;
        this.value=value;
        this.next=null;
    }
}

//链表类
class EntryList{
    Entry head = null;

    // 添加数据
    public int put(Entry entry){
        if(head==null) {
            head=entry;
            return 1;
        }
        Entry curEnt = head;
        while(true){
            //已存在值,替换
            if(curEnt.key==entry.key){
                curEnt.value = entry.value;
                return 0;
            }
            if(curEnt.next==null){
                break;
            }
            curEnt = curEnt.next;
        }
        // 存入新值
        curEnt.next = entry;
        return 1;
    }

    public String get(Entry entry){
        Entry curEnt=head;
        while(curEnt!=null){
            if(entry.key==curEnt.key)
                return curEnt.value;
            curEnt = curEnt.next;
        }
        return null;
    }

    // 移除数据
    public int remove(Entry entry){
        if(head==null) return 0;
        if(head.key==entry.key){
            head=null;
            return -1;
        }
        Entry curEnt = head;
        Entry preEnt = null;
        while(true){
            if (curEnt.key==entry.key){
                preEnt.next=curEnt.next;
                return -1;
            }
            if(curEnt.next==null)
                return 0;
            preEnt = curEnt;
            curEnt = curEnt.next;
        }
    }

    // 遍历链表
    public void list(){
        if(head==null){
            return;
        }
        Entry curEnt = head;
        while(true){
            System.out.println("key:"+curEnt.key+"value:"+curEnt.value);
            if(curEnt.next==null)
                break;
            curEnt = curEnt.next;
        }
    }
}

// 默认key为int类型，不用指定
public class HashTable {

    private int N=0;//键值对总数
    private int M=10;//散列表大小
    private double alpha=2;// N/M的上限
    private EntryList[] table;//链表数组

    public HashTable(){
        table = new EntryList[M];
        for(int i=0;i<M;i++)
            table[i]=new EntryList();
    }

    // 简单的求余hash函数
    private int hash(int key){
        return key % M;
    }

    private void put(int key,String value){
        int index=hash(key);
        Entry entry = new Entry(key,value);
        N+=table[index].put(entry);
        if((N+0.0)/M>alpha) resize(M*2);
    }

    private String get(int key){
        int index=hash(key);
        Entry entry = new Entry(key,"");
        return table[index].get(entry);
    }

    private void remove(int key){
        int index=hash(key);
        Entry entry = new Entry(key,"");
        N+=table[index].remove(entry);
        if((N+0.0)/M<1.0/(alpha*2) && M>10) resize(M/2);
    }

    private void list(){
        for(int i=0;i<table.length;i++)
            table[i].list();
    }


    // 重置散列表大小，重填
    private void resize(int M){
        this.M = M;
        EntryList[] oldTable = table;
        table = new EntryList[M];
        for(int i=0;i<M;i++)
            table[i]=new EntryList();

        for(int i=0;i<oldTable.length;i++){
            Entry curEnt = oldTable[i].head;
            while(curEnt!=null){
                int index = hash(curEnt.key);
                table[index].put(curEnt);
                curEnt=curEnt.next;
            }
        }

    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        for(int i=0;i<50;i++)
            ht.put(i,i+"");
        ht.list();
        System.out.println(ht.M);
        System.out.println(ht.N);
        System.out.println(ht.get(13));
        ht.remove(13);
        System.out.println(ht.get(13));
    }
}


