### 1.生产者丢失消息的情况

根据send结果重新发送、重试



### 2.消费者丢失消息的情况

**手动关闭闭自动提交 offset，每次在真正消费完消息之后之后再自己手动提交 offset 。** 但是，细心的朋友一定会发现，这样会带来消息被重新消费的问题。比如你刚刚消费完消息之后，还没提交 offset，结果自己挂掉了，那么这个消息理论上就会被消费两次。



### 3.Kafka 弄丢了消息

**假如 leader 副本所在的 broker 突然挂掉，那么就要从 follower 副本重新选出一个 leader ，但是 leader 的数据还有一些没有被 follower 副本的同步的话，就会造成消息丢失

#### 设置 acks = all

#### 设置 replication.factor >= 3

#### 设置 min.insync.replicas > 1

**unclean.leader.election.enable = false** 的话，当 leader 副本发生故障时就不会从 follower 副本中和 leader 同步程度达不到要求的副本中选择出 leader ，这样降低了消息丢失的可能性。



 生产者： 1、设置acks，确保消息写入leader及follower后才返回。 2、如果链路不同，就像作者说的重试。 3、重试都不通，考虑持久化后进行批处理；如果是大量请求打不到kafka，生产者的机子要发通知给运维，运维也要对机子的网络进行监控。 Kafka: 1、确保数据及时同步到多副本：acks、replicas、factor的设置。 （注：数据复制（同步）必然有延迟的情况，acks的确认就意味着没写入副本的就当是没有，以防止只写入leader且leader宕机的情况） 消费者： 1、消费消息先持久化，消费完成再确认offset。 2、进行事务化的任务处理。