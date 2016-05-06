package Writter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Model.Sensor;

public class DataWriter {

	public void write(Map<Integer, List<Sensor>> read, int fileName) {
		// TODO Auto-generated method stub
		try {
			PrintWriter writer = new PrintWriter("C:\\arpan\\Thesis\\data_analysis\\data-analysis\\txt_files\\aiLab_4_"+Integer.toString(fileName)+".txt", "UTF-8");
		Set<Integer> keySet = read.keySet();
		for (Integer sensorID : keySet) {
			List<Sensor> list = read.get(sensorID);
			for (Sensor sensor : list) {
				writer.println(sensor.getId()+"\t"+sensor.getStartTime()+"\t"+sensor.getEndTime());
			}
			
		}
		writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
