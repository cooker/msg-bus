package com.cooker.msgbus.rpc;

import com.cooker.msgbus.common.Contants;
import com.cooker.msgbus.common.URLParamType;
import com.cooker.msgbus.core.IParams;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.Map;
/*
 * 版权：
 * 创建者:   ykq
 * 创建时间:  2018/09/05 下午5:31
 * 功能描述: 通用解析URL
 * 修改历史:
 */
public class URL  implements Serializable, IParams {

    private static final long serialVersionUID = -8685592712004589928L;
    //协议
    private String protocol;
    private String host;
    private int port;
    //路径 / 接口名interfaceName
    private String path;
    //参数
    private Map<String, String> params;

    public URL(String protocol, String host, int port, String path){
        this(protocol, host, port, path, Maps.newHashMap());
    }

    public URL(String protocol, String host, int port, String path, Map<String, String> params) {
        this.protocol = protocol;
        this.path = path;
        this.host = host;
        this.port = port;
        this.params = params;
    }

    public static URL valueOf(String url){
        Preconditions.checkArgument(!StringUtils.isBlank(url), "url is null");
        String protocol = StringUtils.substringBefore(url, Contants.PROTOCOL_PREFIX);
        Preconditions.checkArgument(!StringUtils.isBlank(protocol), "protocol is null");
        String eUrl = StringUtils.substringAfter(url, Contants.PROTOCOL_PREFIX);
        String host = StringUtils.substringBefore(eUrl, ":");
        eUrl =  StringUtils.substringAfter(eUrl, ":");
        String path;
        int port = NumberUtils.toInt(StringUtils.substringBefore(eUrl, "/"), -1);
        if (port == -1)
            port = NumberUtils.toInt(StringUtils.substringBefore(eUrl, "?"), -1);
        eUrl = StringUtils.substringAfter(eUrl, "/");
        Map<String, String> params = Maps.newHashMap();
        if(eUrl.contains("?")){
            path = StringUtils.substringBefore(eUrl, "?");
            eUrl = StringUtils.substringAfter(eUrl,"?");
            String[] pms = StringUtils.split(eUrl, Contants.QUERY_PARAM_SEPARATOR);
            for(String str : pms){
                String[] v = str.split("=");
                if(v.length == 2){
                    params.put(v[0], v[1]);
                }
                //过滤无值参数
            }

        }else{
            path = eUrl;
        }
        return new URL(protocol, host, port, path, params);
    }

    public URL copy(){
        Map<String, String> rmap = Maps.newHashMap();
        if(params != null){
            rmap.putAll(params);
        }
        return new URL(protocol, host, port, path, rmap);
    }

    public String getProtocol() {
        return protocol;
    }

    public String getPath() {
        return path;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getGroup(){
        return getString(URLParamType.group.getName(), URLParamType.group.getValue());
    }

    public String getNodeType(){
        return getString(URLParamType.nodeType.getName(), URLParamType.nodeType.getValue());
    }

    public String getVersion(){
        return getString(URLParamType.version.getName(), URLParamType.version.getValue());
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String getString(String key, String dVal) {
        String value = getParams().get(key);
        if(value == null){
            value = dVal;
        }
        return value;
    }

    @Override
    public long getLong(String key, long dVal) {
        String op = getString(key);
        if(StringUtils.isNotEmpty(op)){
            return NumberUtils.toLong(op, dVal);
        }
        return dVal;
    }

    @Override
    public int getInt(String key, int dVal) {
        String op = getString(key);
        if(StringUtils.isNotEmpty(op)){
            return NumberUtils.toInt(op, dVal);
        }
        return dVal;
    }

    @Override
    public boolean getBoolean(String key, boolean dVal) {
        String op = getString(key);
        if(StringUtils.isNotEmpty(op)){
            return BooleanUtils.toBoolean(op);
        }
        return dVal;
    }

    @Override
    public float getFloat(String key, float dVal) {
        String op = getString(key);
        if(StringUtils.isNotEmpty(op)){
            return NumberUtils.toFloat(op, dVal);
        }
        return dVal;
    }

    @Override
    public double getDouble(String key, double dVal) {
        String op = getString(key);
        if(StringUtils.isNotEmpty(op)){
            return NumberUtils.toDouble(op, dVal);
        }
        return dVal;
    }

    public String getUri(){
        return protocol + Contants.PROTOCOL_PREFIX + host + ":" + port + Contants.PATH_SUFFX  + path;
    }

    /**
     * 返回一个service or referer的Identity，如果两个url的Identity相同，
     * 则表示相同的service or referer
     * @return
     */
    public String getIdentity(){
        return protocol + Contants.PROTOCOL_PREFIX + host + ":" + port
                + Contants.PATH_SUFFX  + getGroup()
                + Contants.PATH_SUFFX + getPath()
                + Contants.PATH_SUFFX + getVersion()
                + Contants.PATH_SUFFX + getNodeType();
    }

    public void addParam(String name, String value){
        if(StringUtils.isAnyEmpty(name, value)) return;
        params.put(name, value);
    }

    public void addParams(Map<String, String> rmap){
        params.putAll(rmap);
    }

    public void addParamIfAbsent(String name, String value){
        if(hasParam(name)) return;
        getParams().put(name, value);
    }

    public void removeParam(String name){
        if(!StringUtils.isEmpty(name)){
            getParams().remove(name);
        }
    }

    public boolean hasParam(String name){
        if(StringUtils.isEmpty(name)) return false;
        return getParams().containsKey(name);
    }

    @Override
    public String toString() {
        return toSimpleString();
    }

    public String toSimpleString(){
        return getUri() + "?group=" + getGroup();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        URL url = (URL) o;

        return new EqualsBuilder()
                .append(port, url.port)
                .append(protocol, url.protocol)
                .append(path, url.path)
                .append(host, url.host)
                .append(params, url.params)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(protocol)
                .append(path)
                .append(host)
                .append(port)
                .append(params)
                .toHashCode();
    }
}
