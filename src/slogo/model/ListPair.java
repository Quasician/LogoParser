package slogo.model;

public class ListPair<L,R> {
    private L currentList;
    private R result;

    public ListPair(L currentList, R result)
    {
        this.currentList = currentList;
        this.result = result;
    }

    public L getCurrentList()
    {
        return currentList;
    }

    public R getResult()
    {
        return result;
    }
}
