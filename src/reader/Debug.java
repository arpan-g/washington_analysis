package reader;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Sensor;

public class Debug {
	


		public Map<Integer, List<Sensor>> read() {
			Map<Integer, List<Sensor>> sensorMap = new HashMap<Integer, List<Sensor>>();
				try {
					PrintWriter writer = new PrintWriter("m04.txt", "UTF-8");
					for (String line : Files.readAllLines(Paths.get("ailab_Spring2008.txt"))) {
						String[] split = line.split("\t");
//						if (split[0].trim().equalsIgnoreCase("2008-01-02")) {
							if (split[2].trim().equalsIgnoreCase("M15")) {
								 DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.SSSSSS");

								String dateString = split[0].trim() + " " + split[1].trim();
								DateTime dateTime = format.parseDateTime(dateString);
//								 System.out.println(dateTime.getMillis());
								
								writer.println(line);
//								writer.println(split[0]+"\t"+split[1]);
//								long constant = time-1199000000000L;
//								System.out.println(dateString+"\t"+constant );
								}

//							}
						}
					writer.close();
				}
					catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			return sensorMap;
		}

		private Sensor getLastEntry(Map<Integer, List<Sensor>> sensorMap, int senID) {
			List<Sensor> senList = sensorMap.get(senID);
			int size = senList.size();
			return senList.get(size-1);
		}



}
