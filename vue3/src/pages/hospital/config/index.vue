<template>
  <div>
    <div class="sp-role-left">
      <t-card :bordered="false">
        <div class="sp-role-left-header">
          <t-row>
            <t-col :span="6">
              <t-button>新增医院配置</t-button>
            </t-col>
          </t-row>
        </div>
        <t-table row-key="id" :data="data" :max-height="'calc(98vh - 235px)'" :columns="columns" :table-layout="'fixed'"
          :pagination="pagination" :selected-row-keys="selectedRowKeys" :loading="dataLoading"
          :loading-props="{ size: '23px', text: '加载中...' }" @select-change="rehandleSelectChange"
          @page-change="onPageChange">
          <!-- <template #phone="{ row }">
            {{ row.phone + '/' + row.email }}
          </template> -->
          <template #frpStatus="{ row }">
            <t-tag shape="round"
              :theme="row.frpStatus === 'online' ? 'primary' : row.frpStatus === 'offline' ? 'default' : 'danger'"
              variant="light" size="small">{{
                frpList[row.frpStatus]
              }}</t-tag>
          </template>
          <template #operation="{ row }">
            <t-button size="small" variant="outline" theme="primary">详情</t-button>
            <t-button size="small" variant="outline" theme="default">修改</t-button>
            <t-button size="small" variant="outline" theme="danger">删除</t-button>
          </template>
        </t-table>
      </t-card>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: 'ListTenant',
};
</script>

<script setup lang="ts">
import { ref, onMounted, computed, reactive } from 'vue';
import { MessagePlugin, DialogPlugin } from 'tdesign-vue-next';
import { hospitalConfigList } from '@/api/hospital/config';
import { dicVals } from '@/api/common';

const selectedRowKeys = ref([]);
const data = ref([]);
const dataLoading = ref(false);
const pagination = ref({
  pageSize: 20,
  total: 0,
  current: 1,
});

const frpList = {
  "unknown": "无记录",
  "online": "在线",
  "offline": "离线",
}

const columns = [
  {
    width: 100,
    colKey: 'hospitalName',
    title: '医院名称',
  },
  {
    width: 100,
    colKey: 'ngrokName',
    title: '别名(内网穿透使用)',
    ellipsis: true,
  },
  {
    width: 100,
    colKey: 'frpStatus',
    title: '状态',
    ellipsis: true,
  },
  {
    colKey: 'operation',
    title: '操作',
    width: 160,
    cell: 'operation',
    fixed: 'right',
  },
];

const rehandleSelectChange = (value, { selectedRowData }) => {
  selectedRowKeys.value = value;
};

const onPageChange = async (pageInfo) => {
  pagination.value.current = pageInfo.current;
  pagination.value.pageSize = pageInfo.pageSize;
  await fetchData();
};

const fetchData = async () => {
  data.value = [];
  selectedRowKeys.value = [];
  dataLoading.value = true;

  const queryData = {
    ...pagination.value,
    size: pagination.value.pageSize
  };

  try {
    const {
      data: { records, total },
    } = await hospitalConfigList(queryData);
    data.value = records;
    pagination.value.total = total;
  } catch (er) {
    MessagePlugin.error(er.message);
  } finally {
    dataLoading.value = false;
  }
};


// vue的api
onMounted(async () => {
  fetchData();
});
</script>

<style lang="less" scoped>
@import '@/style/variables';

.menu-active {
  color: var(--td-brand-color) !important;
}

.menu-unactive {
  color: var(--tdvns-text-color-primary) !important;
}

.menu-text {
  vertical-align: middle;
}

.sp-role-left {
  border-radius: 8px;

  .sp-role-left-header {
    padding-bottom: 30px;
  }
}
</style>
