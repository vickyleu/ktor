### 编译器
export CC=armv7-linux-gnueabihf-gcc     
export CXX=armv7-linux-gnueabihf-g++                                                                                                  
export AR=armv7-linux-gnueabihf-ar
export AS=armv7-linux-gnueabihf-as
export LD=armv7-linux-gnueabihf-ld
export RANLIB=armv7-linux-gnueabihf-ranlib
export STRIP=armv7-linux-gnueabihf-strip
export NM=armv7-linux-gnueabihf-nm
export OBJCOPY=armv7-linux-gnueabihf-objcopy
export OBJDUMP=armv7-linux-gnueabihf-objdump
export READELF=armv7-linux-gnueabihf-readelf
export SYSROOT=/usr/local/Cellar/armv7-unknown-linux-gnueabihf/13.3.0/toolchain/armv7-unknown-linux-gnueabihf/sysroot
export PATH=$PATH:/usr/local/Cellar/armv7-unknown-linux-gnueabihf/13.3.0/toolchain/bin

### openssl
./Configure linux-armv4 no-shared no-dso no-hw no-engine --prefix=/Users/vickyleu/Downloads/abc/openssl-1.1.1l/build  -fPIC


### curl
./configure --host=arm-linux-gnueabihf \
--with-ssl=/Users/vickyleu/Downloads/abc/openssl-1.1.1l/build  --prefix=/Users/vickyleu/Downloads/abc/curl-7.78.0/build  \
--disable-shared \
--enable-static \
--disable-ldap \
--disable-ldaps \
--disable-rtsp \
--enable-proxy \
--disable-dependency-tracking \
--without-librtmp \
--without-zlib \
--without-brotli \
--without-zstd \
--without-libidn2 \
--without-nghttp2 \
--without-libpsl \
--without-libssh2


