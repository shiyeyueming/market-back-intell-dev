@ if(coffeeDevDomains.totalPages!=1){ //query 是通过标签传入的参数，对应类PageQuery
@ var pageUrl=has(url)?url:pagePatternUrl(),pageUrlParameter=pageParameterUrl();
<nav aria-label="Page navigation col-md-8 col-xs-12">
  <ul class="pagination pagination-self">
	@if(coffeeDevDomains.totalPages<=10){//小于10页直接全部输出
		@for(var i in range(1,coffeeDevDomains.totalPages+1)){
			<li class="${i==(coffeeDevDomains.number+1)?'active'}"><a href="${ctxPath}${pageUrl}${i}.html${pageUrlParameter}">${i}</a></li>
		@}
	@}else{
		@var maxdiff = coffeeDevDomains.totalPages-(coffeeDevDomains.number+1);
		@var start = coffeeDevDomains.number+1<=4?1:maxdiff<=3?(coffeeDevDomains.totalPages - 7):(coffeeDevDomains.number+1-3);
		@var end = coffeeDevDomains.number+1<=4?7:maxdiff<=3?coffeeDevDomains.totalPages:(coffeeDevDomains.number+1+3);
		
		@if(start>1){
			<li><a href="${ctxPath}${pageUrl}${coffeeDevDomains.number}.html${pageUrlParameter}"><span aria-hidden="true">&lt;</span></a></li>
			<li class="disabled"><a href="javascript:;">···</a></li>
		@}
		
		@for(var i in range(start,end+1)){
			<li class="${i==(coffeeDevDomains.number+1)?'active'}"><a href="${ctxPath}${pageUrl}${i}.html${pageUrlParameter}">${i}</a></li>
		@}
		
		@if(end<coffeeDevDomains.totalPages){
			<li class="disabled"><a href="javascript:;">···</a></li>
			<li><a href="${ctxPath}${pageUrl}${coffeeDevDomains.number+2}.html${pageUrlParameter}"><span aria-hidden="true">&gt;</span></a></li>
		@}
	@}
  </ul>
</nav>
@}