package net.ato.shupapium.utils.acballistics;

public record ShupapiumAmmoBallistics(
        float speed, float spread
) {
    public static final ShupapiumAmmoBallistics DEFAULT = new ShupapiumAmmoBallistics(0F, 0F);
}
