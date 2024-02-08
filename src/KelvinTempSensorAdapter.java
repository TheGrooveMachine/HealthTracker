public class KelvinTempSensorAdapter implements ITempSensor{
   
   private final KelvinTempSensor sensor;      // Temperature sensor.

   public KelvinTempSensorAdapter(){
      sensor = new KelvinTempSensor(); // adapted sensor object
   }
   
   public double reading(){ // override reading in interface
      return sensor.reading();
   }
}