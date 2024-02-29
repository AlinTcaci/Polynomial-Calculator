package data_models;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Double> polynomial = new TreeMap<Integer, Double>(Comparator.reverseOrder());

    public Polynomial(TreeMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public Polynomial() {
        this.polynomial = polynomial;
    }

    public TreeMap<Integer, Double> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(TreeMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public static String toString(Polynomial p) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Double> entry : p.getPolynomial().entrySet()) {
            if (entry.getValue() != 0) {
                if (entry.getKey() > 1) {
                    if (entry.getValue() > 0 && !result.isEmpty()) {
                        if (entry.getValue() == 1) {
                            result.append("+").append("x^").append(entry.getKey());
                        } else {
                            result.append("+").append(entry.getValue()).append("x^").append(entry.getKey());
                        }
                    } else {
                        if (entry.getValue() == 1) {
                            result.append("x^").append(entry.getKey());
                        } else if (entry.getValue() == -1) {
                            result.append("-x^").append(entry.getKey());
                        } else {
                            result.append(entry.getValue()).append("x^").append(entry.getKey());
                        }
                    }
                } else if (entry.getKey() == 1) {
                    if (entry.getValue() > 0 && !result.isEmpty()) {
                        if (entry.getValue() == 1) {
                            result.append("+").append("x");
                        } else {
                            result.append("+").append(entry.getValue()).append("x");
                        }
                    } else {
                        if (entry.getValue() == 1) {
                            result.append("x");
                        } else if (entry.getValue() == -1) {
                            result.append("-x");
                        } else {
                            result.append(entry.getValue()).append("x");
                        }
                    }
                } else {
                    if (entry.getValue() > 0 && !result.isEmpty()) {
                        result.append("+").append(entry.getValue());
                    } else {
                        result.append(entry.getValue());
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.append('0');
        }
        return result.toString();
    }
}
