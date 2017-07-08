package Amazon.OO.OOText;

public final class Replace extends Text {
    private final Text target;
    private final Text searchFor;
    private final Text replaceWith;

    public Replace(Text target, Text searchFor, Text replaceWith) {
        this.target = target;
        this.searchFor = searchFor;
        this.replaceWith = replaceWith;
    }

    public String get() {
        return target.get().replace(searchFor.get(), replaceWith.get());
    }
}
