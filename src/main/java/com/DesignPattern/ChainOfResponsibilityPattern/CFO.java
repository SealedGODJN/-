package com.DesignPattern.ChainOfResponsibilityPattern;

public class CFO implements BudgetHandler {
    private BudgetHandler nextHandler = null;

    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if(amount<50000){
            System.out.println("CFO同意,希望你再接再厉，为公司做出更大的贡献。");
            return true;
        }
        if (nextHandler!=null){
            return nextHandler.handle(amount);
        }
        //已经没有更高级的管理层来处理了
        System.out.println(String.format("%d太多了，回去好好看看能不能缩减一下",amount));
        return false;
    }
}

