package start;

import java.util.List;
import java.util.Map;

import Model.Sensor;
import Writter.DataWriter;
import reader.DataReader;
import reader.Debug;

public class EntryPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Debug db = new Debug();
//		db.read();
//		for(int i =2;i<29;i++){
		int i=1;
		DataReader dr = new DataReader();
		Map<Integer, List<Sensor>> read = dr.read(i);
		DataWriter dw = new DataWriter();
		dw.write(read,i);
//		}
		System.out.println("here");

	}

}
