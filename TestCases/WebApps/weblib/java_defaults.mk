
# makefile fragment to define the macros java_default_version,
# java{,5,6,7,8}_architectures

java8_architectures =
java7_architectures = alpha amd64 arm64 armel armhf arm64 i386 lpia \
		mips mipsel \
		powerpc powerpcspe ppc64 ppc64el \
		s390x sh4 x32
java6_architectures = $(java7_architectures)
java5_architectures = $(java6_architectures) \
		hppa ia64 m68k s390 sparc sparc64 \
		kfreebsd-amd64 kfreebsd-i386 hurd-i386
java_architectures = $(java5_architectures)

_java_host_arch := $(if $(DEB_HOST_ARCH),$(DEB_HOST_ARCH),$(shell dpkg-architecture -qDEB_HOST_ARCH))
ifneq (,$(filter $(_java_host_arch),$(java8_architectures)))
  java_default_version = 8
else ifneq (,$(filter $(_java_host_arch),$(java6_architectures)))
  java_default_version = 7
else ifneq (,$(filter $(_java_host_arch),$(java6_architectures)))
  java_default_version = 6
else ifneq (,$(filter $(_java_host_arch),$(java5_architectures)))
  java_default_version = 5
endif
