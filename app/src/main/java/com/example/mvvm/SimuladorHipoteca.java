package com.example.mvvm;







public class SimuladorHipoteca {

    public static class Solicitud {
        public double capital;
        public int plazo;

        public Solicitud(double capital, int plazo) {
            this.capital = capital;
            this.plazo = plazo;
        }
    }

    interface Callback {
        void cuandoEsteCalculadaLaCuota(double cuota);
        // (i) Los dos metodos de error se pueden simplificar en uno:
        // void cuandoHayaError(Error error);
        void cuandoHayaErrorDeCapitalInferiorAlMinimo(double capitalMinimo);
        void cuandoHayaErrorDePlazoInferiorAlMinimo(int plazoMinimo);
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
    }

    public void calcular(Solicitud solicitud, Callback callback) {

        callback.cuandoEmpieceElCalculo();

        double interes = 0;
        double capitalMinimo = 0;
        int plazoMinimo = 0;

        try {
            Thread.sleep(2500);  // long run operation
            interes = 0.01605;
            capitalMinimo = 1000;
            plazoMinimo = 2;
        } catch (InterruptedException e) {}


        boolean error = false;
        if (solicitud.capital < capitalMinimo) {
            callback.cuandoHayaErrorDeCapitalInferiorAlMinimo(capitalMinimo);
            error = true;
        }

        if (solicitud.plazo < plazoMinimo) {
            callback.cuandoHayaErrorDePlazoInferiorAlMinimo(plazoMinimo);
            error = true;
        }

        if(!error) {
            callback.cuandoEsteCalculadaLaCuota(solicitud.capital * interes / 12 / (1 - Math.pow(1 + (interes / 12), -solicitud.plazo * 12)));
        }

        callback.cuandoFinaliceElCalculo();
    }

    // implementación inicial, se usa return en lugar de callbacks
    public double calcular(Solicitud solicitud) {
        double interes = 0;
        try {
            Thread.sleep(10000);   // long run operation
            interes = 0.01605;
        } catch (InterruptedException e) {}

        return solicitud.capital*interes/12/(1-Math.pow(1+(interes/12),-solicitud.plazo*12));
    }
}