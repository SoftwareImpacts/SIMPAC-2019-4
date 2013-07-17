// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protofiles/NeighborArrayMessage.proto

package rina.encoding.impl.googleprotobuf.neighbor;

public final class NeighborArrayMessage {
  private NeighborArrayMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class neighbors_t extends
      com.google.protobuf.GeneratedMessage {
    // Use neighbors_t.newBuilder() to construct.
    private neighbors_t() {
      initFields();
    }
    private neighbors_t(boolean noInit) {}
    
    private static final neighbors_t defaultInstance;
    public static neighbors_t getDefaultInstance() {
      return defaultInstance;
    }
    
    public neighbors_t getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.internal_static_rina_messages_neighbors_t_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.internal_static_rina_messages_neighbors_t_fieldAccessorTable;
    }
    
    // repeated .rina.messages.neighbor_t neighbor = 1;
    public static final int NEIGHBOR_FIELD_NUMBER = 1;
    private java.util.List<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t> neighbor_ =
      java.util.Collections.emptyList();
    public java.util.List<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t> getNeighborList() {
      return neighbor_;
    }
    public int getNeighborCount() { return neighbor_.size(); }
    public rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t getNeighbor(int index) {
      return neighbor_.get(index);
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t element : getNeighborList()) {
        output.writeMessage(1, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      for (rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t element : getNeighborList()) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, element);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t result;
      
      // Construct using rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t();
        return builder;
      }
      
      protected rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.getDescriptor();
      }
      
      public rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t getDefaultInstanceForType() {
        return rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.neighbor_ != java.util.Collections.EMPTY_LIST) {
          result.neighbor_ =
            java.util.Collections.unmodifiableList(result.neighbor_);
        }
        rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t) {
          return mergeFrom((rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t other) {
        if (other == rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.getDefaultInstance()) return this;
        if (!other.neighbor_.isEmpty()) {
          if (result.neighbor_.isEmpty()) {
            result.neighbor_ = new java.util.ArrayList<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t>();
          }
          result.neighbor_.addAll(other.neighbor_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 10: {
              rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t.Builder subBuilder = rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t.newBuilder();
              input.readMessage(subBuilder, extensionRegistry);
              addNeighbor(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      
      // repeated .rina.messages.neighbor_t neighbor = 1;
      public java.util.List<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t> getNeighborList() {
        return java.util.Collections.unmodifiableList(result.neighbor_);
      }
      public int getNeighborCount() {
        return result.getNeighborCount();
      }
      public rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t getNeighbor(int index) {
        return result.getNeighbor(index);
      }
      public Builder setNeighbor(int index, rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t value) {
        if (value == null) {
          throw new NullPointerException();
        }
        result.neighbor_.set(index, value);
        return this;
      }
      public Builder setNeighbor(int index, rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t.Builder builderForValue) {
        result.neighbor_.set(index, builderForValue.build());
        return this;
      }
      public Builder addNeighbor(rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t value) {
        if (value == null) {
          throw new NullPointerException();
        }
        if (result.neighbor_.isEmpty()) {
          result.neighbor_ = new java.util.ArrayList<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t>();
        }
        result.neighbor_.add(value);
        return this;
      }
      public Builder addNeighbor(rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t.Builder builderForValue) {
        if (result.neighbor_.isEmpty()) {
          result.neighbor_ = new java.util.ArrayList<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t>();
        }
        result.neighbor_.add(builderForValue.build());
        return this;
      }
      public Builder addAllNeighbor(
          java.lang.Iterable<? extends rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t> values) {
        if (result.neighbor_.isEmpty()) {
          result.neighbor_ = new java.util.ArrayList<rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.neighbor_t>();
        }
        super.addAll(values, result.neighbor_);
        return this;
      }
      public Builder clearNeighbor() {
        result.neighbor_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:rina.messages.neighbors_t)
    }
    
    static {
      defaultInstance = new neighbors_t(true);
      rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:rina.messages.neighbors_t)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_rina_messages_neighbors_t_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_rina_messages_neighbors_t_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n%protofiles/NeighborArrayMessage.proto\022" +
      "\rrina.messages\032 protofiles/NeighborMessa" +
      "ge.proto\":\n\013neighbors_t\022+\n\010neighbor\030\001 \003(" +
      "\0132\031.rina.messages.neighbor_tB,\n*rina.enc" +
      "oding.impl.googleprotobuf.neighbor"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_rina_messages_neighbors_t_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_rina_messages_neighbors_t_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_rina_messages_neighbors_t_descriptor,
              new java.lang.String[] { "Neighbor", },
              rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.class,
              rina.encoding.impl.googleprotobuf.neighbor.NeighborArrayMessage.neighbors_t.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          rina.encoding.impl.googleprotobuf.neighbor.NeighborMessage.getDescriptor(),
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}
