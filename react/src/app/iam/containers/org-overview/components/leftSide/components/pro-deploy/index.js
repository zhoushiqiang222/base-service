import React from 'react';
import { observer } from 'mobx-react-lite';
import ContainerBlock from '../../../ContainerBlock';

import './index.less';

const ProDeploy = observer(() => (
  <div className="c7n-overview-prodeploy">
    <ContainerBlock width="100%" height={306}>项目部署</ContainerBlock>
  </div>
));

export default ProDeploy;
