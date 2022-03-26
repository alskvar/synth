package sources.voices;

import sources.*;
import sources.utils.Socket;
import sources.utils.SourceValue;

import static utils.FrequencyManipulations.getFrequencyBySemitones;

public class Voice extends AbstractSignalProcessor implements Gated, Triggerable {

    SourceValue pitch, velocity, aftertouch, releaseVelocity;
    Gated gated;
    Triggerable triggerable;

    public Voice(SourceValue pitch, SourceValue velocity, SourceValue aftertouch, SourceValue releaseVelocity, Gated gated, Triggerable triggerable){
        this(new Socket(), pitch, velocity, aftertouch, releaseVelocity, gated, triggerable);
    }
    public Voice(SignalSource source, SourceValue pitch, SourceValue velocity, SourceValue aftertouch, SourceValue releaseVelocity, Gated gated, Triggerable triggerable) {
        super(source);
        this.pitch = pitch;
        this.velocity = velocity;
        this.aftertouch = aftertouch;
        this.releaseVelocity = releaseVelocity;
        this.gated = gated;
        this.triggerable = triggerable;
    }

    @Override
    public double getSample(int sampleId) {
        return source().getSample(sampleId);
    }

    public void noteOn(int note){
        noteOn(note, 64);
    }

    public void noteOn(int note, int velocity) {
        pitch.setValue(SignalSource.frequencyToVoltage(getFrequencyBySemitones(note)));
        this.velocity.setValue(velocity / 128.0);
        gated.gateOn();
        triggerable.trigger();
    }

    public void noteOff(){
        noteOff(0);
    }

    public void noteOff(int velocity) {
        releaseVelocity.setValue(velocity / 128.0);
        gated.gateOff();
    }

    @Override
    public void gateOn() {
        gated.gateOn();
    }

    @Override
    public void gateOff() {
        gated.gateOff();
    }

    @Override
    public void trigger() {
        triggerable.trigger();
    }

}
