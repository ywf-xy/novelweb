$(function () {
		$.post("/wfRead/novel/getranklist", "",
				function f(date) {
						let bookwords = date.bookwords;
						$("#bookwords").empty();
						console.log(bookwords)
						let updatetime = date.updatetime;
						$("#updatetime").empty();
						console.log(updatetime)
						let monthlytickets = date.monthlytickets;
						$("#monthlytickets").empty();
						console.log(monthlytickets)
						let totalhits = date.totalhits;
						$("#totalhits").empty();
						console.log(totalhits)
						let downloads = date.downloads;
						$("#downloads").empty();
						console.log(downloads)
						let li = "";
						for (let i = 0; i <10 ; i++) {
							li = '<li><b>'+(i+1)+'</b>.&nbsp;<a href="/wfRead/novel/book/'+bookwords[i].book_name+'" target="_blank">'+bookwords[i].book_name+'</a><span>('+bookwords[i].book_author+')</span></li>';
							$("#bookwords").append(li);
							li = '<li><b>'+(i+1)+'</b>.&nbsp;<a href="/wfRead/novel/book/'+updatetime[i].book_name+'" target="_blank">'+updatetime[i].book_name+'</a><span>('+updatetime[i].book_author+')</span></li>';
							$("#updatetime").append(li);
							li = '<li><b>'+(i+1)+'</b>.&nbsp;<a href="/wfRead/novel/book/'+monthlytickets[i].book_name+'" target="_blank">'+monthlytickets[i].book_name+'</a><span>('+monthlytickets[i].book_author+')</span></li>';
							$("#monthlytickets").append(li);
							li = '<li><b>'+(i+1)+'</b>.&nbsp;<a href="/wfRead/novel/book/'+totalhits[i].book_name+'" target="_blank">'+totalhits[i].book_name+'</a><span>('+totalhits[i].book_author+')</span></li>';
							$("#totalhits").append(li);
							li = '<li><b>'+(i+1)+'</b>.&nbsp;<a href="/wfRead/novel/book/'+downloads[i].book_name+'" target="_blank">'+downloads[i].book_name+'</a><span>('+downloads[i].book_author+')</span></li>';
							$("#downloads").append(li);
						}
				}, "json")
})