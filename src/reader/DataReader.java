package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Model.Sensor;

public class DataReader {

	public Map<Integer, List<Sensor>> read(int date) {
		Map<Integer, List<Sensor>> sensorMap = new HashMap<Integer, List<Sensor>>();
		String strDate;
		if(date<10){
			
			 strDate ="2008-04-0"+Integer.toString(date);
		}else{
			strDate ="2008-04-"+Integer.toString(date);
		}
		try {
			// PrintWriter writer = new PrintWriter("washington.txt", "UTF-8");
			for (String line : Files.readAllLines(Paths.get("ailab_Spring2008.txt"))) {
				String[] split = line.split("\t");
				if (split[0].trim().equalsIgnoreCase(strDate)) {
					if (split[2].trim().startsWith("M")) {
						// writer.println(line);
						String sen_str = split[2].replaceFirst("M", "");
						int sen_num = Integer.parseInt(sen_str.trim());
						// System.out.println(sen_num);
						DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.SSSSSS");

						String dateString = split[0].trim() + " " + split[1].trim();
						DateTime dateTime = format.parseDateTime(dateString);
						long time = dateTime.getMillis();
						// System.out.println(time);
						if (split[3].trim().equals("ON")) {
							Sensor sen = new Sensor();
							sen.setId(sen_num);
							sen.setStartTime(time);
							sen.setComplete(false);
							if (sensorMap.containsKey(sen_num)) {
								Sensor senLastEntry = getLastEntry(sensorMap, sen_num);
								Sensor lastEntry = senLastEntry;
								Sensor prevSen = lastEntry;
								if (prevSen.isComplete()) {
									sensorMap.get(sen_num).add(sen);
								} else {
									prevSen.setStartTime(time);
								}
							} else {
								List<Sensor> senList = new ArrayList<Sensor>();
								senList.add(sen);
								sensorMap.put(sen_num, senList);
							}

						} else if (split[3].trim().equals("OFF")) {
							if (sensorMap.containsKey(sen_num)) {
								if (!getLastEntry(sensorMap, sen_num).isComplete()) {
									getLastEntry(sensorMap, sen_num).setEndTime(time);
									getLastEntry(sensorMap, sen_num).setComplete(true);
								}
							}
						}

					}
				}
			}
			// writer.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return sensorMap;
	}

	private Sensor getLastEntry(Map<Integer, List<Sensor>> sensorMap, int senID) {
		List<Sensor> senList = sensorMap.get(senID);
		int size = senList.size();
		return senList.get(size - 1);
	}

}
