package com.beobal;

import org.apache.cassandra.thrift.TFramedTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class VerboseTransportFactory extends TFramedTransportFactory
{

    @Override
    public TTransport openTransport(TSocket socket) throws TTransportException
    {
        System.out.println("******Opening Transport*******");
        return super.openTransport(socket);
    }

}
