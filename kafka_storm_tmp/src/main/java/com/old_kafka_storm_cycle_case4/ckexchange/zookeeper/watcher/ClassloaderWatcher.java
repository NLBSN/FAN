package com.old_kafka_storm_cycle_case4.ckexchange.zookeeper.watcher;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

import com.old_kafka_storm_cycle_case4.ckexchange.utils.InitUtils;

/**
 * @author fan
 * @description 类加载的观察者
 */
public class ClassloaderWatcher implements Watcher {
    private static final Logger LOG = Logger.getLogger(ClassloaderWatcher.class);
    private String zkServer = null;
    private String nodePath = null;

    public ClassloaderWatcher(String zkServer, String nodePath) {
        this.zkServer = zkServer;
        this.nodePath = nodePath;
    }

    @Override
    public void process(WatchedEvent event) {
        EventType type = event.getType();
        //当出现节点数据改变时进行重新加载
        if (type == EventType.NodeDataChanged) {
            LOG.info("-------------again init-------------------");
            boolean flag = InitUtils.init(zkServer, nodePath);
            if (flag) {
                LOG.info("-------------init success-----------------");
            } else {
                LOG.info("-------------init failure-----------------");
            }

        } else {
            LOG.info("------------event type-------------------" + type.toString());
        }
    }

}
