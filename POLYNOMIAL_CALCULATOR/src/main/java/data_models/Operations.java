package data_models;

import data_models.Polynomial;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());

        map.putAll(p1.getPolynomial());

        for (Map.Entry<Integer, Double> entry : p2.getPolynomial().entrySet()) {
            if (map.containsKey(entry.getKey())) {
                map.put(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        result.setPolynomial(map);
        return result;
    }

    public static Polynomial diff(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());

        map.putAll(p1.getPolynomial());

        for (Map.Entry<Integer, Double> entry : p2.getPolynomial().entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                map.put(entry.getKey(), -entry.getValue());
            } else {
                map.put(entry.getKey(), map.get(entry.getKey()) - entry.getValue());
            }
        }
        result.setPolynomial(map);
        return result;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Double> entry1 : p1.getPolynomial().entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : p2.getPolynomial().entrySet()) {
                if (!map.containsKey(entry1.getKey() + entry2.getKey())) {
                    map.put(entry1.getKey() + entry2.getKey(), entry1.getValue() * entry2.getValue());
                } else {
                    map.put(entry1.getKey() + entry2.getKey(), map.get(entry1.getKey() + entry2.getKey()) + entry1.getValue() * entry2.getValue());
                }
            }
        }
        result.setPolynomial(map);
        return result;
    }

    public static void cleanPoly(Polynomial p) {
        Iterator<Map.Entry<Integer, Double>> iter = p.getPolynomial().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Double> deg = iter.next();
            if (deg.getValue().equals(0.0)) {
                iter.remove();
            }
        }
    }

    public static String divide(Polynomial p1, Polynomial p2) {
        Polynomial cat = new Polynomial();
        Polynomial rest;
        rest = p1;
        cleanPoly(p2);
        if (p2.getPolynomial().isEmpty()) {
            return "You can't DIVIDE by 0!";
        }
        while (!rest.getPolynomial().isEmpty() && rest.getPolynomial().firstKey() >= p2.getPolynomial().firstKey()) {
            int gradRest = rest.getPolynomial().firstKey();
            int gradP2 = p2.getPolynomial().firstKey();
            int grad = gradRest - gradP2;
            double coeffRest = rest.getPolynomial().get(gradRest);
            double coeffP2 = p2.getPolynomial().get(gradP2);
            double coeff = coeffRest / coeffP2;
            Polynomial term = new Polynomial();
            term.getPolynomial().put(grad, coeff);
            cat.getPolynomial().put(grad, coeff);
            Polynomial product = multiply(term, p2);
            rest = diff(rest, product);
            cleanPoly(rest);
        }
        return "quotient = " + Polynomial.toString(cat) + " rest = " + Polynomial.toString(rest);
    }

    public static Polynomial derivative(Polynomial p) {
        Polynomial result = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Double> entry : p.getPolynomial().entrySet()) {
            if (entry.getKey() != 0) {
                map.put(entry.getKey() - 1, entry.getKey() * entry.getValue());
            }
        }

        result.setPolynomial(map);
        return result;
    }

    public static Polynomial integrate(Polynomial p) {
        Polynomial result = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());
        DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<Integer, Double> entry : p.getPolynomial().entrySet()) {
            // Double will be transformed into string with form "#.##" and then it will be transformed back to double
            map.put(entry.getKey() + 1, Double.parseDouble(df.format(entry.getValue() / (entry.getKey() + 1))));
        }
        result.setPolynomial(map);
        return result;
    }

    public static Polynomial doPolyMap(String s) {
        Polynomial p = new Polynomial();
        Polynomial invalid = new Polynomial();
        TreeMap<Integer, Double> map = new TreeMap<>(Comparator.reverseOrder());
        Pattern pattern = Pattern.compile("([+-]?\\d*)[a-zA-Z](\\^(\\d+))?|([+-]?\\d+)");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            double coefficient;
            int exponent = 0;
            String match = matcher.group();
            if (match.charAt(0) == '+' || match.charAt(0) == '-') {
                // remove sign, if present
                match = match.substring(1);
            }
            // term with variable
            int varIndex = match.indexOf('x');
            if (varIndex == 0) {
                // coefficient is 1
                coefficient = 1;
                String expStr = match.substring(varIndex + 1);
                exponent = expStr.isEmpty() ? 1 : Integer.parseInt(expStr.substring(1));
            } else if (varIndex == -1) {
                // just a coefficient or invalid input
                try {
                    coefficient = Double.parseDouble(match);
                } catch (NumberFormatException e) {
                    return invalid;
                }
            } else {
                // extract coefficient and exponent
                String coeffStr = match.substring(0, varIndex);
                coefficient = Double.parseDouble(coeffStr);
                String expStr = match.substring(varIndex + 1);
                exponent = expStr.isEmpty() ? 1 : Integer.parseInt(expStr.substring(1));
            }
            if (matcher.group().startsWith("-")) {
                coefficient = -coefficient;
            }
            map.put(exponent, coefficient);
        }
        p.setPolynomial(map);
        return p;
    }

    public static String returnResult(String p1, String p2, String op) {
        Polynomial poly1;
        Polynomial poly2;
        poly1 = doPolyMap(p1);
        poly2 = doPolyMap(p2);
        if (op.equals("DERIVATE") || op.equals("INTEGRATE")) {
            if (poly1.getPolynomial().isEmpty()) {
                return "Invalid input at first polynomial";
            }
        } else if (poly1.getPolynomial().isEmpty() || poly2.getPolynomial().isEmpty()) {
            return "Invalid input";
        }
        return switch (op) {
            case "ADD" -> "The result is " + Polynomial.toString(add(poly1, poly2));
            case "SUBTRACT" -> "The result is " + Polynomial.toString(diff(poly1, poly2));
            case "MULTIPLY" -> "The result is " + Polynomial.toString(multiply(poly1, poly2));
            case "DIVIDE" -> divide(poly1, poly2);
            case "DERIVATE" -> "The result is " + Polynomial.toString(derivative(poly1));
            case "INTEGRATE" -> "The result is " + Polynomial.toString(integrate(poly1));
            default -> null;
        };
    }
}
