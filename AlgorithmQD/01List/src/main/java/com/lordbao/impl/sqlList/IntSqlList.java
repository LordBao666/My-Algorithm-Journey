package com.lordbao.impl.sqlList;

import com.lordbao.MyList;
import com.lordbao.utils.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


/**
 * @Author Lord_Bao
 * @Date 2024/10/2 21:22
 * @Version 1.0
 */
@Slf4j
public class IntSqlList implements MyList<Integer> {
    private final static Integer maxSize=20;
    private final Integer [] data = new Integer[maxSize];
    private int size;

    public IntSqlList(){
        initList();
    }

    public boolean isFull(){
        return size==maxSize;
    }

    @Override
    public Status initList() {
        for(int i=0;i<maxSize;i++){
            data[i]=0;
        }
        size=0;
        return Status.OK;
    }

    @Override
    public Status clearList() {
        for(int i=0;i<maxSize;i++){
            //帮助垃圾回收
            data[i]=null;
        }
        size=0;
        return Status.OK;
    }

    @Override
    public Status insertList(int i, Integer ele) {
        if(i<0 || i>size){
            log.error("insertList fails!");
            log.error("The position {} is out of index between 0 and {}",i,size);
            return Status.OVERFLOW;
        }
        if(isFull()){
            log.warn("Sorry,the list is FULL!");
            return Status.OVERFLOW;
        }
        for(int j=size-1;j>=i;j--){
            data[j+1]=data[j];
        }
        data[i]=ele;
        size++;
        return Status.OK;
    }

    /**
     *
     * 删除位置i处的元素. 注意，如果删除失败仍然会返回null
     * 因此请查看日志信息.
     */
    @Override
    public Integer deleteList(int i) {
        if(i<0 || i>size-1){
            log.error("deleteList fails!");
            log.error("The position {} is out of index between 0 and {}",i,size-1);
            return null;
        }
        Integer result = data[i];
        for(int j=i+1;j<size;j++){
            data[j-1]=data[j];
        }
        data[size-1]=null;//帮助垃圾回收
        size--;
        return result;
    }

    @Override
    public int listLength() {
        return size;
    }


    @Override
    public int locateElem(Integer ele) {
        for(int i=0;i<size;i++){
            if(Objects.equals(data[i], ele)){
                return i;
            }
        }
        log.info("{} is not found!",ele);
        return -1;
    }

    /**
     *
     * 返回位置i处的元素. 注意，如果下标不对仍然会返回null
     * 因此请查看日志信息.
     */
    @Override
    public Integer getElem(int i) {
        if(i<0 || i>size-1){
            log.error("getElem fails!");
            log.error("The position {} is out of index between 0 and {}",i,size-1);
            return null;
        }
        return data[i];
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "IntList {}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("IntList { ");
        for(int i=0;i<size-1;i++){
            sb.append(data[i]).append(",");
        }
        sb.append(data[size-1]).append(" }");
        return sb.toString();
    }
}
