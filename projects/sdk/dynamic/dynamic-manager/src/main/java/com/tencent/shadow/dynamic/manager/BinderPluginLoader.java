package com.tencent.shadow.dynamic.manager;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.tencent.shadow.dynamic.loader.PluginLoader;
import com.tencent.shadow.dynamic.loader.PluginServiceConnection;

import java.util.Map;

class BinderPluginLoader implements PluginLoader {
    final private IBinder mRemote;

    BinderPluginLoader(IBinder remote) {
        mRemote = remote;
    }

    @Override
    public void loadPlugin(String partKey) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeString(partKey);
            mRemote.transact(TRANSACTION_loadPlugin, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override
    public Map getLoadedPlugin() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        Map _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_getLoadedPlugin, _data, _reply, 0);
            _reply.readException();
            ClassLoader cl = (ClassLoader) this.getClass().getClassLoader();
            _result = _reply.readHashMap(cl);
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public void callApplicationOnCreate(String partKey) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeString(partKey);
            mRemote.transact(TRANSACTION_callApplicationOnCreate, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    @Override
    public Intent convertActivityIntent(Intent pluginActivityIntent) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        Intent _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            if ((pluginActivityIntent != null)) {
                _data.writeInt(1);
                pluginActivityIntent.writeToParcel(_data, 0);
            } else {
                _data.writeInt(0);
            }
            mRemote.transact(TRANSACTION_convertActivityIntent, _data, _reply, 0);
            _reply.readException();
            if ((0 != _reply.readInt())) {
                _result = Intent.CREATOR.createFromParcel(_reply);
            } else {
                _result = null;
            }
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public ComponentName startPluginService(Intent pluginServiceIntent) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        ComponentName _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            if ((pluginServiceIntent != null)) {
                _data.writeInt(1);
                pluginServiceIntent.writeToParcel(_data, 0);
            } else {
                _data.writeInt(0);
            }
            mRemote.transact(TRANSACTION_startPluginService, _data, _reply, 0);
            _reply.readException();
            if ((0 != _reply.readInt())) {
                _result = ComponentName.CREATOR.createFromParcel(_reply);
            } else {
                _result = null;
            }
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public boolean stopPluginService(Intent pluginServiceIntent) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        boolean _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            if ((pluginServiceIntent != null)) {
                _data.writeInt(1);
                pluginServiceIntent.writeToParcel(_data, 0);
            } else {
                _data.writeInt(0);
            }
            mRemote.transact(TRANSACTION_stopPluginService, _data, _reply, 0);
            _reply.readException();
            _result = (0 != _reply.readInt());
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public boolean bindPluginService(Intent pluginServiceIntent, PluginServiceConnection connection, int flags) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        boolean _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            if ((pluginServiceIntent != null)) {
                _data.writeInt(1);
                pluginServiceIntent.writeToParcel(_data, 0);
            } else {
                _data.writeInt(0);
            }
            _data.writeStrongBinder((((connection != null)) ? (new PluginServiceConnectionBinder(connection)) : (null)));
            _data.writeInt(flags);
            mRemote.transact(TRANSACTION_bindPluginService, _data, _reply, 0);
            _reply.readException();
            _result = (0 != _reply.readInt());
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    @Override
    public void unbindService(PluginServiceConnection conn) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeStrongBinder((((conn != null)) ? (new PluginServiceConnectionBinder(conn)) : (null)));
            mRemote.transact(TRANSACTION_unbindService, _data, _reply, 0);
            _reply.readException();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }
}