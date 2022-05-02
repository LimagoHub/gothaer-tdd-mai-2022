package de.gothaer.tiere;

public class Schwein {

    private String name;
    private int gewicht;

    public Schwein() {
        this("Nobody");
    }

    public Schwein(String name) {
        setName(name);
        setGewicht(10);
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if(name == null || "Elsa".equalsIgnoreCase(name))
            throw new IllegalArgumentException("Upps");
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    private void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schwein{");
        sb.append("name='").append(name).append('\'');
        sb.append(", gewicht=").append(gewicht);
        sb.append('}');
        return sb.toString();
    }
}
