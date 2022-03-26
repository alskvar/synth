package sources;

import sources.utils.*;

public interface SignalSource {
    int sampleRate = 44100;
    double samplingPeriod = 1.0 / sampleRate;
    double minFrequency = 1, maxFrequency = 20000;

    double getSample(int sampleId);

    static double frequencyToVoltage(double frequency) {
        return Math.log(frequency / minFrequency) / Math.log(maxFrequency / minFrequency);
    }

    static double voltageToFrequency(double voltage) {
        return Math.pow(maxFrequency / minFrequency, voltage) * minFrequency;
    }

    static double frequencyRatioToVoltage(double frequencyRatio){
        return Math.log(frequencyRatio) / Math.log(maxFrequency / minFrequency);
    }

    default SignalSource attenuate(double coefficient){
        return new Attenuator(this, new DC(coefficient));
    }

    default SignalSource attenuate(SignalSource coefficientSource){
        return new Attenuator(this, coefficientSource);
    }

    default SignalSource add(double value){
        return this.add(new DC(value));
    }

    default SignalSource add(SignalSource valueSource){
        return new Mixer(this, valueSource);
    }

    default SignalSource sub(double value){
        return this.add(-value);
    }

    default SignalSource sub(SignalSource valueSource){
        return this.add(valueSource.attenuate(-1));
    }

    default SignalSource mapUni(double min, double max){
        return this.attenuate(max-min).add(min);
    }
    default SignalSource mapBi(double min, double max){
        return this.attenuate((max-min)/2).add((min+max)/2);
    }

    default SignalSource clipUni(){
        return new Clip(this, false);
    }

    default SignalSource clipBi(){
        return new Clip(this, true);
    }

    default SignalSource multiplyFrequency(double ratio){
        return this.add(frequencyRatioToVoltage(ratio));
    }

    default SignalSource addFrequency(double frequency){
        return new FrequencyAdder(this, frequency);
    }
}
