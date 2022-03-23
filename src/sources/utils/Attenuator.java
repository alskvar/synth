package sources.utils;

import sources.AbstractSignalSource;
import sources.SignalSource;

public class Attenuator extends AbstractSignalSource {

    SignalSource source;
    SignalSource coefficientSource;
    double lastSample;

    public Attenuator(SignalSource source, SignalSource coefficientSource){
        this.source = source;
        this.coefficientSource = coefficientSource;
    }

    public void setCoefficient(SignalSource coefficientSource){
        this.coefficientSource = coefficientSource;
    }
    public double getCoefficient(int sampleId){
        return coefficientSource.getSample(sampleId);
    }

    @Override
    public double getSample(int sampleId) {
        if(checkAndUpdateSampleId(sampleId))
            lastSample = source.getSample(sampleId) * getCoefficient(sampleId);
        return lastSample;
    }
}