abstract class HypotheticalAbstract1 {
    protected int a;
    protected int b;

    public HypotheticalAbstract1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public abstract int getValue1();
    public abstract int getValue2();
}

abstract class HypotheticalAbstract2 extends HypotheticalAbstract1 {
    public HypotheticalAbstract2(int a, int b) {
        super(a, b);
    }

    @Override
    public int getValue1() {
        return this.a + this.b;
    }
}

abstract class HypotheticalAbstract3 extends HypotheticalAbstract2 {
    public HypotheticalAbstract3(int a, int b) {
        super(a, b);
    }

    @Override
    public int getValue2() {
        return this.a * this.b;
    }
}

class HypotheticalNonAbstract extends HypotheticalAbstract3 {
    public HypotheticalNonAbstract(int a, int b) {
        super(a, b);
    }
}