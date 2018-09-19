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

public class TrackInputFormat extends FileInputFormat<LongWritable, Text>
{
  public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context)
  {
    return new TrackRecordReader();
  }

  protected boolean isSplitable(JobContext context, Path file)
  {
    CompressionCodec codec = new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
    return codec == null;
  }
}