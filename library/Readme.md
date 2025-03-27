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
COMPILER_PATH="${SYSROOT}/../../lib/gcc/armv7-unknown-linux-gnueabihf/13.3.0/"


[//]: # (export CC="clang --target=armv7-linux-gnueabihf --sysroot=$SYSROOT -L${COMPILER_PATH} --gcc-toolchain=${SYSROOT}/../../bin")
[//]: # (export CXX="clang++ --target=armv7-linux-gnueabihf --sysroot=$SYSROOT -L${COMPILER_PATH} --gcc-toolchain=${SYSROOT}/../../bin")


[//]: # (export CC="armv7-linux-gnueabihf-gcc --target=armv7-linux-gnueabihf --sysroot=$SYSROOT -L${COMPILER_PATH} --gcc-toolchain=${SYSROOT}/../../bin")
[//]: # (export CXX="armv7-linux-gnueabihf-g++ --target=armv7-linux-gnueabihf --sysroot=$SYSROOT -L${COMPILER_PATH} --gcc-toolchain=${SYSROOT}/../../bin")


### 添加架构相关编译标志


export LDFLAGS="--sysroot=$SYSROOT \
  -L$SYSROOT/../../lib/gcc/armv7-unknown-linux-gnueabihf/13.3.0"
export CFLAGS="$CFLAGS $LDFLAGS  -march=armv7-a -mfpu=neon -mfloat-abi=hard"
export CXXFLAGS="$CXXFLAGS $LDFLAGS  -march=armv7-a -mfpu=neon -mfloat-abi=hard"

# 通过环境变量传递 CFLAGS 和 LDFLAGS
export CFLAGS="${CFLAGS} -fPIC -I${SYSROOT}/usr/include"
export CXXFLAGS="${CXXFLAGS} -fPIC -I${SYSROOT}/usr/include"
export LDFLAGS="${LDFLAGS} -L${SYSROOT}/usr/lib"

### openssl
cd openssl-3.4.1

# 清理旧配置
make clean 2>/dev/null

# 配置命令
# 添加 no-secure-getenv 选项

./Configure linux-armv4 \
no-dso \
no-hw \
no-engine \
-fPIC \
--prefix=/Volumes/Extra/Github/ktor/library/openssl-3.4.1/build



**./Configure linux-armv4 \
no-shared \
no-dso \
no-hw \
no-engine \
-fPIC \
--prefix=/Volumes/Extra/Github/ktor/library/openssl-3.4.1/build**


# 编译并安装
make depend
make -j$(nproc)
make install_sw        # 仅安装软件，不包含文档


### curl
cd curl-7.78.0

# 清理旧配置
make distclean 2>/dev/null
rm -f config.cache

# 配置命令
./configure --host=armv7-linux-gnueabihf \
--prefix=/Volumes/Extra/Github/ktor/library/curl-8.12.1/build --with-ssl=/Volumes/Extra/Github/ktor/library/openssl-3.4.1/build \
--enable-static \
--enable-shared \
--disable-verbose  --enable-websockets \
--disable-idn --disable-http2 --without-gssapi \
--without-libnghttp2 --disable-libnghttp2 \
--without-libssh2 --without-libidn2 \
--disable-cookies \
--disable-dict \
--disable-file \
--disable-ftp \
--disable-gopher \
--disable-imap \
--disable-ldap \
--disable-ldaps \
--disable-pop3 \
--disable-proxy \
--disable-rtsp \
--disable-smtp \
--disable-telnet \
--disable-tftp  --without-zlib --without-libpsl --without-librtmp \
CFLAGS="${CFLAGS} -I${SYSROOT}/usr/include -I/Volumes/Extra/Github/ktor/library/openssl-3.4.1/build/include" \
LDFLAGS="${LDFLAGS} -L${SYSROOT}/usr/lib -L/Volumes/Extra/Github/ktor/library/openssl-3.4.1/build/lib"

# 编译并安装
make -j$(nproc)
make install


# libz.a编译
# 重要, 这里需要配置交叉编译器,否则生成的makefile会有问题
export CHOST="armv7-linux-gnueabihf-gcc" 
./configure --prefix=$PWD/build  --host=armv7-linux-gnueabihf  //--static


