package com.vdata.analysis.track.format;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 * 类描述：以整个UV访问情况作为单条记录输入
 * 
 * @author: 58.com
 * @date： 日期：2013-9-24 时间：下午3:10:48
 */
public class TrackInputFormat extends FileInputFormat<LongWritable, Text> {

	@SuppressWarnings("deprecation")
	@Override
	public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) {
		return new TrackRecordReader();
	}

	@Override
	protected boolean isSplitable(JobContext context, Path file) {
		CompressionCodec codec = new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
		return codec == null;
	}

}
