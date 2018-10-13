package com.golden.raspberry.awards.util;

import java.io.File;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.golden.raspberry.awards.domain.Movie;

public final class  CsvUtil {

	  public static <T> List<T> loadObjectList(Class<T> type, String fileName) {
		    try {
		    	
		    	CsvSchema bootstrapSchema =
		    			CsvSchema.builder()
		    			    .addColumn("year")
		    				.addColumn("title")
		    				.addArrayColumn("studios", ',')
		    				.addArrayColumn("producers", ',')
		    				.addColumn("winner")
		    				.build()
		    				.withColumnSeparator(';')
		    				.withHeader();
		    			                             
		        
		    	CsvMapper mapper = new CsvMapper();
		        
		        File file = new ClassPathResource(fileName).getFile();
		        	        
		        MappingIterator<T> readValues = 
		        mapper.reader(type).with(bootstrapSchema).readValues(file);
		        
		       
		        return readValues.readAll();
		    
		    } catch (Exception e) {
		        return Collections.emptyList();
		    }
		}  
	
}
